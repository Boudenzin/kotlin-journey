package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun MakeLemonade(modifier: Modifier = Modifier) {

    var lemonadePhase = 0

    val lemonadeImage = when (lemonadePhase) {
        0 -> painterResource(id = R.drawable.lemon_tree)
        1 -> painterResource(id = R.drawable.lemon_squeeze)
        2 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }

    var lemonadeContent = when(lemonadePhase) {
        0 -> stringResource(id = R.string.lemon_tree_content_description)
        1 -> stringResource(id = R.string.lemon_squeeze_content_description)
        2 -> stringResource(id = R.string.lemon_drink_content_description)
        else -> stringResource(id = R.string.lemon_restart_content_description)
    }

    var sentenceLemonade = when (lemonadePhase) {
        0 -> stringResource(id = R.string.lemon_tree)
        1 -> stringResource(id = R.string.lemon_squeeze)
        2 -> stringResource(id = R.string.lemon_drink)
        else -> stringResource(id = R.string.lemon_restart)
    }

    Column (){

        Button(
            onClick = {lemonadePhase = (lemonadePhase + 1) % 4},
        ) {
            Image(
                painter = lemonadeImage,
                contentDescription = lemonadeContent,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .padding(16.dp)
            )
        }

        Text(
            sentenceLemonade,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .padding(16.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        MakeLemonade(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}