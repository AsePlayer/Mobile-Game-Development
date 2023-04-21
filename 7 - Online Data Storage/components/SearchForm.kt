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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.ryan.jokesapp.data.JokeModel

@Composable
fun SearchForm(doSearch: (String) -> Unit) {
    val TEXTBOXPADDING = 16.dp //5.dp
    Column() {
        // this variable contains the value of the textfield
        var textboxState by remember {
            mutableStateOf("")
        }
        // object that is used to handle focus events (active status
        val focusManager = LocalFocusManager.current

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

                        //  trim removes spaces or carriage return (enter) characters
                        doSearch(textboxState.trim())
                    }
                    if(it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_TAB) {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    true
                },
            leadingIcon = {
                IconButton(onClick = {/* TODO */}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Icon"
                    )
                }
            },
            label = {
                Text(text ="Search for a joke!")
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    doSearch(textboxState.trim())
                }
            ))
    }
}