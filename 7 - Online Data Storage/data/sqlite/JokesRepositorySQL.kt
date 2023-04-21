package com.ryan.jokesapp.data.sqlite

import com.ryan.jokesapp.data.JokeModel

class JokesRepositorySQL (val jokesDao: JokesDaoSQLite) {

    // use suspend for each of these to allow for coroutine calls
    // this will make the functions "main safe".
    // the UI will not freeze during slow database operations.
    suspend fun getAll() = jokesDao.getAll()
    suspend fun findByKeyword(phrase: String) = jokesDao.getWithSearch(phrase)
    suspend fun addOne(joke: JokeModel) = jokesDao.addOne(joke)
    suspend fun deleteOne(joke: JokeModel) = jokesDao.deleteById(joke.id)

}