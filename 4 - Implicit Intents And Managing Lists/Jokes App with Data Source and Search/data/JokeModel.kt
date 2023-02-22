package com.ryan.jokesapp.data

import android.util.Log

class JokeModel(
    val id: Int,
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
}