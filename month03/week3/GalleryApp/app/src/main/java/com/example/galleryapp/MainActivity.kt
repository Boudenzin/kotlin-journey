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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
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

data class Artwork(
    val imageRes: Int,
    val titleRes: Int,
    val artistRes: Int
)

val artworks = listOf(
    Artwork(R.drawable.autoportrait, R.string.artwork_title_0, R.string.artwork_artist_0),
    Artwork(R.drawable.rhytmic_lines, R.string.artwork_title_1, R.string.artwork_artist_1),
    Artwork(R.drawable.sisifo, R.string.artwork_title_2, R.string.artwork_artist_2),
    Artwork(R.drawable.stockholm, R.string.artwork_title_3, R.string.artwork_artist_3),
    Artwork(R.drawable.dyogenes, R.string.artwork_title_4, R.string.artwork_artist_4)

)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryAppTheme {

                val windowSizeClass = calculateWindowSizeClass(this)


                GalleryScreen(windowSizeClass = windowSizeClass)
            }
        }
    }
}

@Composable
fun LayoutEmRetrato(
    artwork: Artwork,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize() // Usa o modifier recebido
    ) {
        Column (
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            // Usa o artwork recebido via parâmetro
            Image(
                painter = painterResource(artwork.imageRes),
                contentDescription = stringResource(artwork.titleRes),
                modifier = Modifier
                    .shadow(elevation = 8.dp, shape = RectangleShape)
                    .padding(32.dp)
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                // Usa o artwork recebido via parâmetro
                Text(
                    text = stringResource(artwork.titleRes),
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = stringResource(artwork.artistRes),
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Row (
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                // Chama as funções recebidas via parâmetro
                Button(onClick = onPreviousClick) {
                    Text(
                        stringResource(R.string.botao_prev),
                        fontFamily = ralewayFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                // Chama as funções recebidas via parâmetro
                Button(onClick = onNextClick) {
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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GalleryScreen(modifier: Modifier = Modifier, windowSizeClass: WindowSizeClass) {

    var carouselCounter by remember { mutableIntStateOf(0) }
    val currentArt = artworks[carouselCounter]

    fun handlePrevious() {
        carouselCounter = (carouselCounter - 1 + artworks.size) % artworks.size
    }

    fun handleNext() {
        carouselCounter = (carouselCounter + 1) % artworks.size
    }

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LayoutEmRetrato(
                artwork = currentArt,
                onPreviousClick = ::handlePrevious,
                onNextClick = ::handleNext,
                modifier = modifier
            )
        }

        else -> {
            LayoutEmPaisagem(
                artwork = currentArt,
                onPrevious = ::handlePrevious,
                onNext = ::handleNext,
                modifier = modifier
            )
        }
    }
}



@Composable
fun GalleryWithDescription(modifier: Modifier = Modifier) {

    var carouselCounter by remember { mutableIntStateOf(0)}

    val currentArt = artworks[carouselCounter]

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
                painter = painterResource(currentArt.imageRes),
                contentDescription = stringResource(currentArt.titleRes),
                modifier = Modifier
                    .shadow(elevation = 8.dp, shape = RectangleShape, ambientColor = Color.Black.copy(alpha = 0.5f), spotColor = Color.Black.copy(alpha = 0.5f))
                    .padding(32.dp)
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(currentArt.titleRes),
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = stringResource(currentArt.artistRes),
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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GalleryAppTheme {
        val windowSizeClass = calculateWindowSizeClass(this)


        GalleryScreen(windowSizeClass = windowSizeClass)
    }
}