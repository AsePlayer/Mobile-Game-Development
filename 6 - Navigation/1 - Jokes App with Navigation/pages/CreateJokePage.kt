package com.ryan.jokesappwithnavigation.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CreateJokePage(navController: NavHostController) {
    val joke = remember { mutableStateOf("")}
    val punchline = remember { mutableStateOf("")}
    val buttonEnabled = remember { mutableStateOf(false)}

    Column(modifier = Modifier.padding(20.dp)) {
        Text("Jokes list page")
        Button(onClick = {navController.navigate("login")}) {
            Text(text = "Go to login page (p1)")
        }
        OutlinedTextField(
            value = joke.value,
            onValueChange = { data -> joke.value = data
                buttonEnabled.value = !(punchline.value == "" || joke.value == "") },
            label = {Text("Joke")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = punchline.value,
            onValueChange = {data -> punchline.value = data
                buttonEnabled.value = !(punchline.value == "" || joke.value == "") },
            label = {Text("Punchline")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                Log.d("CreateJokePage", "joke = ${joke.value} punchline = ${punchline.value}")

                // ensure we get a string value from the page state properties
                val j: String = joke.value
                val p: String = punchline.value

                // add two parameters to send these values to the next page
                navController.navigate("jokesPage/${j}/${p}")
            },
            enabled = buttonEnabled.value
        ) {
            Text("Joke!")
        }
    }
}