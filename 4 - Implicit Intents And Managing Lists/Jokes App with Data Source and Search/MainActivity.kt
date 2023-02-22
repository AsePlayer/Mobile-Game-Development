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
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ryan.jokesapp.data.JokeModel
import com.ryan.jokesapp.data.inmemory.ViewModelInMemory
import com.ryan.jokesapp.ui.theme.JokesAppTheme

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
        val viewModel: ViewModelInMemory = ViewModelInMemory()

        MainScreen(
            jokesList = viewModel.jokesList,
            jokesSearchResult = viewModel.jokesSearchResult,
            doAddJoke = {viewModel.addJoke(it)},
            doSearch = {viewModel.findJokesByKeyword(it)},
            doShowHide = {viewModel.hideShowJoke(it)}
        )
    }

    @Composable
    fun MainScreen(
        jokesList : List<JokeModel>,
        jokesSearchResult: List<JokeModel>,
        doAddJoke: (JokeModel) -> Unit,
        doSearch: (String) -> Unit,
        doShowHide: (JokeModel) -> Unit
    ) {
        var jokeText by remember { mutableStateOf("") } // track the text in the input field
        var jokePunchlineText by remember { mutableStateOf("") } // track the text in the input field
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column() {
                // Text input field for entering jokes
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = jokeText,
                    onValueChange = { jokeText = it },
                    label = { Text("Enter a joke") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = jokePunchlineText,
                    onValueChange = { jokePunchlineText = it },
                    label = { Text("Enter a punchline") }
                )

                // Button to add the joke to the list
                Button(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(16.dp),
                    onClick = {
                        val newJoke =
                            JokeModel(
                                jokesList.size,
                                jokeText,
                                jokePunchlineText,
                                true
                            )

                        doAddJoke(newJoke)
                        jokeText = "" // clear the input field
                        jokePunchlineText = "" // clear the input field
                    }
                ) {
                    Text("Add Joke")
                }

                LazyColumn() {
                    //Log.d("Main", "MainScreen: show all ${jokesList.size} jokes")

                    items(jokesList.size) {index ->
                        Joke1(
                            joke = jokesList[index]
                        ) {
                            Log.d("Main","Show ${index}")
                            doShowHide(jokesList[index])
                        }
                    } // items
                } // lazy column
            } // column
        } // surface
    } // main screen


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
} // main activity

