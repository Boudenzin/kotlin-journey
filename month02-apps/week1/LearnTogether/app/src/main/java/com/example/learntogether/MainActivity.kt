package com.example.learntogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.learntogether.ui.theme.LearnTogetherTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnTogetherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JetpackCard(
                        modifier = Modifier.padding(innerPadding).padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun JetpackCard( modifier: Modifier = Modifier) {
    Column {
        Image(
            painter = painterResource(R.drawable.bg_compose_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth().height(194.dp)
        )
        Text(
            text = stringResource(R.string.jetpack_compose_title),
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = stringResource(R.string.jetpack_compose_paragraph1),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify

        )
        Text(
            text = stringResource(R.string.jetpack_compose_paragraph2),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnTogetherTheme {
        JetpackCard(
            modifier = Modifier.padding(16.dp)
        )
    }
}