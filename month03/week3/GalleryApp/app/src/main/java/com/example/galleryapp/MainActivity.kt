package com.example.galleryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GalleryAppTheme {
        Greeting("Android")
    }
}