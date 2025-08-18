package com.example.courses

import android.os.Bundle
import androidx.compose.material3.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
            }
        }
    }
}


@Composable
fun CoursesApp() {
    val layoutDirection = LocalLayoutDirection.current

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection)
            ),
    )
    {
        TopicsList(
            topicList = DataSource().loadTopics(),
        )
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Row(modifier = modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = topic.imageResourceId),
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = LocalContext.current.getString(topic.stringResourceId),
                modifier = Modifier.weight(2f).padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun TopicsList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyColumn (modifier = modifier){
        items(topicList) {topic ->
            TopicCard(
                topic = topic,
                modifier = Modifier.padding(9.dp)
            )
        }
    }
}

@Preview
@Composable
fun TopicCardPreview() {
    TopicCard(Topic(R.string.film, 165, R.drawable.film))
}