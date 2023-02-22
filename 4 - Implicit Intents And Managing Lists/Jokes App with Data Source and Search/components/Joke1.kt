package com.ryan.jokesapp.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ryan.jokesapp.data.JokeModel

@Composable
fun Joke1(joke: JokeModel, changeVisibility: (id: Int) -> Unit) {
    Text(
        text = "Joke #${joke.id}"
    )
    Text(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                changeVisibility(joke.id)
                Log.d("Joke Tag", "Joke1: $joke")
            },
        color = Color.DarkGray,
        fontSize = 36.sp,
        textAlign = TextAlign.Center,
        text = joke.question
    )
    if (joke.answerIsVisible) {
        Text(
            modifier = Modifier.padding(10.dp).background(Color.LightGray).fillMaxWidth(),
            color = Color.Red,
            fontSize = 5.em,
            text = joke.answer
        )
    }
    TabRowDefaults.Divider()
}