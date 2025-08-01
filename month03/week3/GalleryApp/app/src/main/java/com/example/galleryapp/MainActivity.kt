package com.example.galleryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.galleryapp.ui.theme.GalleryAppTheme

val ralewayFamily = FontFamily(
    Font(R.font.raleway_thin, FontWeight.Thin),           // Peso 100
    Font(R.font.raleway_extralight, FontWeight.ExtraLight), // Peso 200
    Font(R.font.raleway_light, FontWeight.Light),         // Peso 300
    Font(R.font.raleway_regular, FontWeight.Normal),      // Peso 400 (Padrão)
    Font(R.font.raleway_medium, FontWeight.Medium),       // Peso 500
    Font(R.font.raleway_semibold, FontWeight.SemiBold),   // Peso 600
    Font(R.font.raleway_bold, FontWeight.Bold),           // Peso 700
    Font(R.font.raleway_extrabold, FontWeight.ExtraBold), // Peso 800
    Font(R.font.raleway_heavy, FontWeight.Black)          // Peso 900 ("Heavy" geralmente mapeia para "Black")
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GalleryWithDescription(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GalleryWithDescription(modifier: Modifier = Modifier) {

    var carouselCounter by remember { mutableIntStateOf(0)}

    val artImage = when(carouselCounter) {
        0 -> painterResource(R.drawable.autoportrait)
        1 -> painterResource(R.drawable.rhytmic_lines)
        2 -> painterResource(R.drawable.sisifo)
        3 -> painterResource(R.drawable.stockholm)
        else -> painterResource(R.drawable.dyogenes)
    }

    val artTitle = when(carouselCounter) {
        0 -> stringResource(R.string.artwork_title_0)
        1 -> stringResource(R.string.artwork_title_1)
        2 -> stringResource(R.string.artwork_title_2)
        3 -> stringResource(R.string.artwork_title_3)
        else -> stringResource(R.string.artwork_title_4)
    }

    val artistName = when(carouselCounter) {
        0 -> stringResource(R.string.artwork_artist_0)
        1 -> stringResource(R.string.artwork_artist_1)
        2 -> stringResource(R.string.artwork_artist_2)
        3 -> stringResource(R.string.artwork_artist_3)
        else -> stringResource(R.string.artwork_artist_4)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = artImage,
                contentDescription = artTitle,
                modifier = Modifier
                    .shadow(elevation = 8.dp, shape = RectangleShape, ambientColor = Color.Black.copy(alpha = 0.5f), spotColor = Color.Black.copy(alpha = 0.5f))
                    .padding(32.dp)
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = artTitle,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = artistName,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Row (
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Button(onClick = {
                    carouselCounter = (carouselCounter - 1 + 5) % 5 // Loop back to the last image if at the first one
                }

                ) {
                    Text(
                        stringResource(R.string.botao_prev),
                        fontFamily = ralewayFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Button(onClick = {
                    carouselCounter = (carouselCounter + 1) % 5
                }

                ) {
                    Text(
                        stringResource(R.string.botao_next),
                        fontFamily = ralewayFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GalleryAppTheme {
        GalleryWithDescription()
    }
}