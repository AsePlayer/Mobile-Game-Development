package com.ryan.jokesapp.components

import android.view.KeyEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.ryan.jokesapp.data.JokeModel
import com.ryan.jokesapp.data.inmemory.ViewModelInMemory


@Composable
fun DataEntryForm(doAddJoke: (JokeModel) -> JokeModel) {
    val TEXTBOXPADDING = 16.dp //5.dp
    Column() {
        // this variable contains the value of the textfield
        var textboxState by remember {
            mutableStateOf("")
        }
        // object that is used to handle focus events (active status
        val focusManager = LocalFocusManager.current

        // second text field to get the joke answer.
        var answerTextboxState by remember {
            mutableStateOf("")
        }

        OutlinedTextField(value = textboxState,
            // Update value of textValue with the latest value of the text field
            onValueChange = {it ->
                textboxState = it},
            modifier = Modifier
                .padding(TEXTBOXPADDING)
                .fillMaxWidth()
                .onKeyEvent {
                    if(it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        focusManager.clearFocus()
                    }
                    if(it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_TAB) {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    true
                },
        leadingIcon = {
            IconButton(onClick = {/* TODO */}) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Icon"
                )
            }
        },
        label = {
            Text(text ="Enter a joke!")
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ))
        OutlinedTextField(value = answerTextboxState,
            // Update value of textValue with the latest value of the text field
            onValueChange = {it ->
                answerTextboxState = it},
            modifier = Modifier
                .padding(TEXTBOXPADDING)
                .fillMaxWidth()
                .onKeyEvent {
                    if(it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        focusManager.clearFocus()
                        doAddJoke(
                            JokeModel(
                                0,
                                question = textboxState,
                                answer = answerTextboxState,
                                true
                            )
                        )
                    }
                    if(it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_TAB) {
                        focusManager.moveFocus(FocusDirection.Up)
                    }
                    true
                },
            leadingIcon = {
                IconButton(onClick = {/* TODO */}) {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "Icon"
                    )
                }
            },
            label = {
                Text(text ="Enter a punchline!")
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    // function to handle the creation of a new joke
                    doAddJoke(JokeModel(
                        0,
                        question = textboxState,
                        answer = answerTextboxState,
                        true
                    ))
                }
            ))
    }
}