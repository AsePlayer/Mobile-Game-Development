package com.ryan.jokesapp.data

interface JokesViewModelInterface {
    // this interface defines all of the operations the app will perform
    // with the jokes collection.

    var jokesList : MutableList<JokeModel>

    var jokesSearchResult : MutableList<JokeModel>

    // get all jokes from the repository.
    fun getAllJokes()

    // add a joke to the repository.
    fun addJoke(joke: JokeModel)

    // remove a joke.
    fun removeJoke(joke : JokeModel)

    // fetch jokes by a key phrase
    fun findJokesByKeyword(findPhrase : String)

    // toggle a joke visible status from true to false / false to true
    fun hideShowJoke(joke : JokeModel)
}