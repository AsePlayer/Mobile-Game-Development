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
import com.ryan.jokesapp.components.DataEntryForm
import com.ryan.jokesapp.components.Joke1
import com.ryan.jokesapp.components.SearchForm
import com.ryan.jokesapp.data.JokeModel
import com.ryan.jokesapp.data.inmemory.ViewModelInMemory
import com.ryan.jokesapp.data.sqlite.JokesViewmodelSQLite
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
        // val viewModel: ViewModelInMemory = ViewModelInMemory()
        val viewModel: JokesViewmodelSQLite = JokesViewmodelSQLite(context = applicationContext)
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
                DataEntryForm(doAddJoke = doAddJoke as (JokeModel) -> JokeModel)
                SearchForm(doSearch = doSearch)
                LazyColumn() {
                    //Log.d("Main", "MainScreen: show all ${jokesList.size} jokes")
                    if(jokesSearchResult.isNotEmpty()) {
                        items(jokesSearchResult.size) { index ->
                            Joke1(
                                joke = jokesSearchResult[index]
                            ) {
                                Log.d("Main", "Show ${index}")
                                doShowHide(jokesSearchResult[index])
                            }
                        } // items
                    } //if
                    else {
                        // show all jokes
                        items(jokesList.size) { index ->
                            Joke1(
                                joke = jokesList[index]
                            ) {
                                doShowHide(jokesList[index])
                            }
                        }
                    } // else
                } // lazy column
            } // column
        } // surface
    } // main screen
} // main activity

