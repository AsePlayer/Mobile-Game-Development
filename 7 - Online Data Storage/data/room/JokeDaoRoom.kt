package com.ryan.jokesapp.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDaoRoom {
    // add a joke to the repository. return the joke if successfully added, return null if failure
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addOne(joke : JokeEntity)

    // remove a joke. Return true if success, false if failure
    @Delete
    fun removeJoke(joke : JokeEntity)

    // remove a joke. Return true if success, false if failure
    @Query("SELECT * FROM jokes_table WHERE joke_id = :id")
    fun getById(id : Int) : Flow<JokeEntity>

    // return a list of jokes whose question or answer contains the search phrase
    // return null if no matches found
    @Query("SELECT * FROM jokes_table WHERE joke_question LIKE :findPhrase OR joke_answer LIKE :findPhrase")
    fun getWithSearch(findPhrase : String) : Flow<List<JokeEntity>>

    // get the entire list of jokes in the collection
    @Query("SELECT * FROM jokes_table")
    fun getAll() : Flow<List<JokeEntity>>
}