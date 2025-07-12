package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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

    var lemonadePhase = remember { mutableStateOf(0) }

    var tapsNeeded = remember { mutableStateOf(getRandomTapsNeeded()) }

    val lemonadeImage = when (lemonadePhase.value) {
        0 -> painterResource(id = R.drawable.lemon_tree)
        1 -> painterResource(id = R.drawable.lemon_squeeze)
        2 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }

    var lemonadeContent = when(lemonadePhase.value) {
        0 -> stringResource(id = R.string.lemon_tree_content_description)
        1 -> stringResource(id = R.string.lemon_squeeze_content_description)
        2 -> stringResource(id = R.string.lemon_drink_content_description)
        else -> stringResource(id = R.string.lemon_restart_content_description)
    }

    var sentenceLemonade = when (lemonadePhase.value) {
        0 -> stringResource(id = R.string.lemon_tree)
        1 -> stringResource(id = R.string.lemon_squeeze)
        2 -> stringResource(id = R.string.lemon_drink)
        else -> stringResource(id = R.string.lemon_restart)
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Button(
            onClick = {
                when (lemonadePhase.value) {
                    0 -> {
                        lemonadePhase.value = (lemonadePhase.value + 1) % 4
                        tapsNeeded.value = getRandomTapsNeeded()
                    }
                    1 -> {
                        tapsNeeded.value -= 1
                        if (tapsNeeded.value <= 0) {
                            lemonadePhase.value = 2
                        }
                    }
                    else -> {
                        lemonadePhase.value = (lemonadePhase.value + 1) % 4
                    }
                }
            },
            shape = RoundedCornerShape(48.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.background_green))
        ) {
            Image(
                painter = lemonadeImage,
                contentDescription = lemonadeContent,
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            sentenceLemonade,
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .padding(16.dp)

        )
    }
}

fun getRandomTapsNeeded(): Int {
    return (2..8).random()
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