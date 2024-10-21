package com.example.grid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grid.model.Datasource
import com.example.grid.model.Topic
import com.example.grid.ui.theme.GridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //GridApp()
                    GridList(
                        topicList = Datasource().topics
                    )
                }
            }
        }
    }
}

/*@Composable
fun GridApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        GridList(
            topicList = Datasource().topics
        )
    }
}*/


@Composable
fun GridCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {

        Row (modifier = Modifier
            .clip(RoundedCornerShape(percent = 10))
            .fillMaxWidth()
            .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourceId),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
            )

            Column {
                Text(
                    text = LocalContext.current.getString(topic.stringResourceId),
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp),
                )

                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = "Icon",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(start = 8.dp, end = 4.dp)
                    )

                    Text(
                        text = topic.numberOfCourses.toString(),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun GridList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        modifier = modifier) {
        items(topicList) { topic ->
                GridCard(
                    topic = topic,
                    modifier = Modifier.padding(8.dp)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GridTheme {
        GridCard(
            Topic(R.string.architecture, 154 ,R.drawable.architecture),
            modifier = Modifier
        )
    }
}