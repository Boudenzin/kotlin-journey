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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cartaovisita.ui.theme.CartaoVisitaTheme

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
            modifier = Modifier.align(Alignment.Center)
        ){
            Image(
                painter = bdphoto,
                contentDescription = null,
            )
            Text(
                text = stringResource(R.string.card_name),
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                color = colorText
            )
            Text(
                text = stringResource(R.string.subtitle_card),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                color = colorText
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Row {
                Column(){
                    Text(
                        text = stringResource(R.string.phone_number),
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        color = colorText
                    )
                    Text(
                        text = stringResource(R.string.email_address),
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                        color = colorText
                    )
                    Text(
                        text = stringResource(R.string.instagram),
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                        color = colorText
                    )
                    Text(
                        text = stringResource(R.string.github),
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                        color = colorText
                    )
                }
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