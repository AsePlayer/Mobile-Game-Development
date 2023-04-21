package com.ryan.jokesapp.data.sqlite

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ryan.jokesapp.data.JokeModel
import com.ryan.jokesapp.data.JokesViewModelInterface
import kotlinx.coroutines.launch

// This class holds the list of jokes
// It implements the JokeDataInterface so it will have methods for accessing the list
// and providing data to the application.
// ViewModel is an interface defined by Android to provide a persistant source of data to
// the application

class JokesViewmodelSQLite (context: Context) : JokesViewModelInterface, ViewModel() {
    // get an instance of the dao
    val jokesDao = JokesDaoSQLite(context, null)

    // get an instance of the repository. Provide the dao
    val repository = JokesRepositorySQL(jokesDao)

    // two lists of data are shared with the app
    // first is a list of all jokes in the collection
    // second is a list of all jokes that match a search request
    // the methods of this class will populate these lists
    override var jokesList : MutableList<JokeModel> = mutableStateListOf<JokeModel>()
    override var jokesSearchResult : MutableList<JokeModel> = mutableStateListOf<JokeModel>()

    init {
        Log.d("JokesViewModel", "INIT is called")
        getAllJokes()
    }

    override fun getAllJokes() {
        // viewModelScope is a coroutine launcher in Android,
        // which we will use for our suspended functions
        viewModelScope.launch {
            jokesList.clear()
            jokesList.addAll(repository.getAll())
        }
    }

    override fun addJoke(joke: JokeModel) {
        viewModelScope.launch {
            // save the joke to the db
            repository.addOne(joke)

            // add to the UI
            jokesList.add(joke)

            // if search results were displayed then add the new joke to the UI
            if(jokesSearchResult.size > 0)
                jokesSearchResult.add(joke)
        }
    }

    override fun removeJoke(joke: JokeModel) {
        viewModelScope.launch {
            // remove from the db
            repository.deleteOne(joke)

            // remove from the UI
            jokesList.remove(joke)
            jokesSearchResult.remove(joke)
        }
    }

    override fun findJokesByKeyword(findPhrase: String) {
        viewModelScope.launch {
            var results = repository.findByKeyword(phrase = findPhrase)
            jokesSearchResult.clear()
            jokesSearchResult.addAll(results)
        }
    }

    override fun hideShowJoke(joke: JokeModel) {
        // hide all joke answers for every item in the list
        for(j in jokesList) {
            j.answerIsVisible = false
        }

        // find which joke we want to show the answer for
        var position : Int = jokesList.indexOf(joke)

        // we must replace at least one entire joke in the list in order to trigger a UI refresh
        // replace the joke in the list with a new copy of the joke that has a "true" visible property
        if(position >= 0)
            jokesList[position] = jokesList[position].copy(answerIsVisible = true)

        // repeat the process for the search results list

        for(j in jokesSearchResult) {
            j.answerIsVisible = false
        }

        // find which joke we want to show the answer for
        var position2 : Int = jokesSearchResult.indexOf(joke)

        // we must replace at least one entire joke in the list in order to trigger a UI refresh
        // replace the joke in the list with a new copy of the joke that has a "true" visible property
        if(position2 >= 0)
            jokesSearchResult[position2] = jokesSearchResult[position2].copy(answerIsVisible = true)
    }
}