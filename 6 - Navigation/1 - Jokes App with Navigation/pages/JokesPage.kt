package com.ryan.jokesappwithnavigation.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun JokesPage(navController: NavHostController, username: String, password: String) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text("Jokes list page")
        Text("Your username = $username and your password = $password")
        Button(onClick = {navController.navigate("createJokePage")}) {
            Text(text = "Go to Add Joke page (p3)")
        }
    }
}