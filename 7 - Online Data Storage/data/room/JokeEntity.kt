package com.ryan.jokesapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ryan.jokesapp.data.JokeModel

@Entity(tableName = "jokes_table")
data class JokeEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "joke_id") val id: Int,
    @ColumnInfo(name = "joke_question") var question : String,
    @ColumnInfo(name = "joke_answer") var answer : String
)
// used to convert an entity into a model
fun JokeEntity.toModel() : JokeModel {
    return JokeModel(id, question, answer, false)
}