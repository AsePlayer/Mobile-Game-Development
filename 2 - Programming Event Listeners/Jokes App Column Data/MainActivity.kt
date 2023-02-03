package com.ryan.jokesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ryan.jokesapp.ui.theme.JokesAppTheme

data class JokeModel(
    val id: Int,
    var question: String,
    var answer: String,
    var answerIsVisible: Boolean
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokesAppTheme {
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp() {
        var jokes = remember {
            mutableStateListOf(
                JokeModel(
                    0,
                    "What is black and white and red all over?",
                    "Penguin in a blender!",
                    answerIsVisible = true
                ),
                JokeModel(
                    1,
                    "Knock knock",
                    "Who's there?",
                    answerIsVisible = false
                ),
                JokeModel(
                    2,
                    "Why did the tomato turn red?",
                    "Because it saw the salad dressing!",
                    answerIsVisible = true
                ),
                JokeModel(
                    3,
                    "Why were the police searching for hardened criminals?",
                    "They stole all the Viagra!",
                    answerIsVisible = true
                )

            )
        }

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn() {
                items(jokes.size) { index ->
                    Joke1(joke = jokes[index],
                        {
                            Log.d("Joke Tag", "MyApp: You clicked joke number $it")
                            // replace the joke at position 'it' with a new joke.
                            // replacing on element wil cause the mutableStateListOf
                            // variable to re-render the UI elements
                            // notice the ! character that toggles the boolean values for answerIsVisible.
                            jokes[it] = JokeModel(it, jokes[it].question, jokes[it].answer, !jokes[it].answerIsVisible)
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun Joke1(joke: JokeModel, changeVisibility: (id: Int) -> Unit) {
        Text(
            text = "Joke #${joke.id}"
        )
        Text(
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    changeVisibility(joke.id)
                    Log.d("Joke Tag", "Joke1: $joke")
                },
            color = Color.DarkGray,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            text = joke.question
        )
        if (joke.answerIsVisible) {
            Text(
                modifier = Modifier.padding(10.dp).background(Color.LightGray).fillMaxWidth(),
                color = Color.Red,
                fontSize = 5.em,
                text = joke.answer
            )
        }
        Divider()

    }
}