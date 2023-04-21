package com.ryan.jokesapp.data

import android.util.Log
import com.ryan.jokesapp.data.room.JokeEntity

class JokeModel(
    var id: Int,
    var question: String,
    var answer: String,
    var answerIsVisible: Boolean
) {
    fun copy(answerIsVisible: Boolean): JokeModel {
        Log.d("JokeModel",this.answerIsVisible.toString())
        return JokeModel(
            this.id,
            this.question,
            this.answer,
            answerIsVisible
        )
    }

    // used to convert a model into an Entity
    fun toEntity() : JokeEntity {
        return JokeEntity(id, question, answer)
    }
}