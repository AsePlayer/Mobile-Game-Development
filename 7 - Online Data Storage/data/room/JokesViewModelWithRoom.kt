package com.ryan.jokesapp.data.room

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryan.jokesapp.data.JokeModel
import com.ryan.jokesapp.data.JokesViewModelInterface
import com.shadsluiter.jokesapp.data.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class JokesViewModelWithRoom (context : Context) : JokesViewModelInterface, ViewModel() {
    // three items needed to connect to the room database
    val db = AppDatabase.getInstance(context)
    val jokeDao = db.jokeDao()
    var jokesRepository = JokesRepository(jokeDao)

    // two lists to connect to the UI
    override var jokesList: MutableList<JokeModel> = mutableStateListOf<JokeModel>()
    override var jokesSearchResult : MutableList<JokeModel> = mutableStateListOf<JokeModel>()

    init {
        getAllJokes()
    }

    override fun getAllJokes() {
        viewModelScope.launch {
            jokesList.clear()
            jokeDao.getAll().collect() {
                response -> for (entity in response) {
                    jokesList.add(entity.toModel())
            }
            }
        }
    }

    override fun addJoke(joke : JokeModel) {
        viewModelScope.launch(Dispatchers.IO) {
            jokesRepository.addOne(joke.toEntity())
            jokesList.add(joke)
        }
    }

    override fun removeJoke(joke : JokeModel) {
        viewModelScope.launch(Dispatchers.IO) {
            jokesRepository.deleteOne(joke.toEntity())
        }
    }

    override fun findJokesByKeyword(findPhrase: String) {
        viewModelScope.launch {
            jokesList.clear()
            jokesRepository.findByKeyword(findPhrase).collect() {
                response -> for (entity in response) {
                    jokesList.add(entity.toModel())
                    Log.d("jokesviewmodel", "findJokesByKeyword: found $entity and how have ${jokesList.size} jokes")
            }
            }
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
        // replace the joke in the list with a new copy of the joke that has a "true" visble property
        if(position >= 0)
            jokesList[position] = jokesList[position].copy(answerIsVisible = true)

        // repeat the process for the search result list
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