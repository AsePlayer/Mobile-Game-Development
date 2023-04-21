package com.ryan.jokesapp.data.inmemory

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.ryan.jokesapp.data.JokeModel
import com.ryan.jokesapp.data.JokesViewModelInterface

class ViewModelInMemory : JokesViewModelInterface, ViewModel() {
    // mutableStateListOf is both mutable (changable such as ArrayList) and observable.
    // The UI automatially refreshes when it senses a change in an observable list of data

    override var jokesList: MutableList<JokeModel> = mutableStateListOf<JokeModel>()
    override var jokesSearchResult: MutableList<JokeModel> = mutableStateListOf<JokeModel>()

    init {
        Log.d("JokeDataSource", "INIT is called")

        jokesList.add(
                JokeModel(
                    0,
                    "What is black and white and red all over?",
                    "Penguin in a blender!",
                    true
                ))
        jokesList.add(JokeModel(
                    1,
                    "Knock knock",
                    "Who's there?",
                    false
                ))
        jokesList.add(JokeModel(
                    2,
                    "Why did the tomato turn red?",
                    "Because it saw the salad dressing!",
                    true
                ))
        jokesList.add(JokeModel(
                    3,
                    "Why were the police searching for hardened criminals?",
                    "They stole all the Viagra!",
                    true
                ))
    }

    override fun getAllJokes() {
        // no processing needed. jokesList has no external data source in this viewmodel design.
        // this function will be used to get all jokes from a database in other versions
    }

    override fun addJoke(joke: JokeModel) {
        joke.id = jokesList.size
        jokesList.add(joke)
    }

    override fun removeJoke(joke: JokeModel) {
        jokesList.remove(joke)
    }

    override fun findJokesByKeyword(findPhrase: String) {
        // reset the list to contain no jokes
        jokesSearchResult.clear()

        // loop through the list of jokes.
        for(joke in jokesList) {
        // find a match in either the question or the answer
        // use lowercase on both the search phrase and the joke
        // this will make the search not case-sensitive
            if(joke.question.lowercase().contains(findPhrase) || joke.answer.lowercase().contains(findPhrase)) {
                // add the joke to the found list
                jokesSearchResult.add(joke)
            }
        }
        // when finished with the for loop, the list might contain some jokes
        // since the list changes, the UI will automatically re-draw
    }

    // change a joke to visible. hide all other joke answers.
    override fun hideShowJoke(joke: JokeModel) {
        // hide all jokes
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

        var position2 : Int = jokesSearchResult.indexOf(joke)

        // we must replace at least one entire joke in the list in order to trigger a UI refresh
        // replace the joke in the list with a new copy of the joke that has a "true" visible property
        if(position2 >= 0)
            jokesSearchResult[position2] = jokesSearchResult[position2].copy(answerIsVisible = true)

    }
    public fun getJokeTotal() : Int {
        return jokesList.size
    }
}