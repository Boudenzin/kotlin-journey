package com.example.gridlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
    Column {
        Row {
            Column {
                Text(
                    text = stringResource(id = R.string.title_1),
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.title_1_subtitle)
                )
            }
            Column {
                Text(
                    text = stringResource(id = R.string.title_2),
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.title_2_subtitle)
                )
            }
        }
        Row {
            Column {
                Text(
                    text = stringResource(id = R.string.title_3),
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.title_3_subtitle)
                )
            }
            Column {
                Text(
                    text = stringResource(id = R.string.title_3),
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.title_3_subtitle)
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