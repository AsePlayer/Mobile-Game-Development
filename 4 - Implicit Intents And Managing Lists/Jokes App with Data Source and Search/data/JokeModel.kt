package com.ryan.jokesapp.data

class JokeModel(
    val id: Int,
    var question: String,
    var answer: String,
    var answerIsVisible: Boolean
) {
    fun copy(answerIsVisible: Boolean): JokeModel {
        this.answerIsVisible = answerIsVisible
        return this
    }
}