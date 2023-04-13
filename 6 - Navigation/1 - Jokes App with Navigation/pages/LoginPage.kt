package com.ryan.jokesappwithnavigation.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginPage(navController: NavHostController) {
    val username = remember { mutableStateOf("")}
    val password = remember { mutableStateOf("")}
    val buttonEnabled = remember { mutableStateOf(false)}

    Surface(modifier = Modifier.padding(20.dp)) {
        Column() {
            Text("Login Page")
            OutlinedTextField(
                value = username.value,
                onValueChange = { data -> username.value = data
                    buttonEnabled.value = !(password.value == "" || username.value == "") },
                label = {Text("Username")}
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = {data -> password.value = data
                    buttonEnabled.value = !(password.value == "" || username.value == "") },
                label = {Text("Password")}
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    Log.d("LoginPage", "name = ${username.value} pw = ${password.value}")

                    // ensure we get a string value from the page state properties
                    val u: String = username.value
                    val p: String = password.value

                    // add two parameters to send these values to the next page
                    navController.navigate("jokesPage/${u}/${p}")
                },
                enabled = buttonEnabled.value
            ) {
                Text("Login")
            }
        }
    }
}