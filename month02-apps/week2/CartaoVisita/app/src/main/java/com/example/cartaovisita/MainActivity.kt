package com.example.cartaovisita

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cartaovisita.ui.theme.CartaoVisitaTheme

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
            CartaoVisitaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BackgroundImage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BackgroundImage(modifier: Modifier = Modifier) {

    val image = painterResource(R.drawable.bd_background_card)
    val bdphoto = painterResource(R.drawable.bd_photo)
    val colorText = Color.White

    Box (modifier = Modifier.fillMaxSize()) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column (
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = bdphoto,
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
                    .size(128.dp, 128.dp)
                    .clip(CircleShape),
            )
            Text(
                text = stringResource(R.string.card_name),
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                color = colorText,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.ExtraBold,
            )
            Text(
                text = stringResource(R.string.subtitle_card),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                color = colorText,
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Bold,
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
             Row (
                 verticalAlignment = Alignment.CenterVertically,
             ) {
                 Icon(
                     imageVector = Icons.Filled.Phone,
                     contentDescription = "telefone",
                     modifier = Modifier.padding(top = 8.dp),
                     tint = Color.White
                 )
                 Text(
                     text = stringResource(R.string.phone_number),
                     modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                     color = colorText,
                     fontFamily = ralewayFamily,
                     fontWeight = FontWeight.Normal,
                 )
             }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "email",
                    modifier = Modifier.padding(top = 4.dp),
                    tint = Color.White
                )
                Text(
                    text = stringResource(R.string.email_address),
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                    color = colorText,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Normal,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.instagram),
                    contentDescription = "instagram",
                    modifier = Modifier.padding(top = 4.dp)
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Text(
                    text = stringResource(R.string.instagram),
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                    color = colorText,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Normal,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.github),
                    contentDescription = "github",
                    modifier = Modifier.padding(top = 4.dp)
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Text(
                    text = stringResource(R.string.github),
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                    color = colorText,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CartaoVisitaTheme {
        BackgroundImage()
    }
}