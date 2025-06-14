package com.example.gridlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridlayout.ui.theme.GridLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridLayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GridLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GridLayout(modifier: Modifier = Modifier) {
    Column (modifier = modifier)
        {
        Row (modifier = Modifier.weight(1f)) {
            Column (modifier = Modifier.weight(1f)
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.title_1),
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.title_1_subtitle),
                    textAlign = TextAlign.Justify
                )
            }
            Column (modifier = Modifier.weight(1f)
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.title_2),
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.title_2_subtitle),
                    textAlign = TextAlign.Justify
                )
            }
        }
        Row (modifier = Modifier.weight(1f)){
            Column (modifier = Modifier.weight(1f)
                .fillMaxSize()){
                Text(
                    text = stringResource(id = R.string.title_3),
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.title_3_subtitle),
                    textAlign = TextAlign.Justify
                )
            }
            Column (modifier = Modifier.weight(1f)
                .fillMaxSize()){
                Text(
                    text = stringResource(id = R.string.title_4),
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.title_4_subtitle),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GridLayoutTheme {
        GridLayout()
    }
}