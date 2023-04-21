package com.ryan.jokesapp.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.ryan.jokesapp.data.JokeModel

@Composable
fun Joke2(joke: JokeModel, context: Context) {
    Text(text = joke.question,
        androidx.compose.ui.Modifier
            .padding(20.dp)
            .clickable {
                Toast
                    .makeText(context, joke.answer, Toast.LENGTH_LONG)
                    .show()
            }
    )
    Divider()
}