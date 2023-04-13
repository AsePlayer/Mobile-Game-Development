package com.ryan.jokesappwithnavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ryan.jokesappwithnavigation.pages.CreateJokePage
import com.ryan.jokesappwithnavigation.pages.JokesPage
import com.ryan.jokesappwithnavigation.pages.LoginPage
import com.ryan.jokesappwithnavigation.ui.theme.JokesAppWithNavigationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokesAppWithNavigationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar() {
                            Text(modifier = Modifier.padding(4.dp), text = "My App")
                        }
                    },
                    bottomBar = {
                        BottomNavigation{
                            BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
                                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home Icon")
                            })
                            BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
                                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
                            })
                            BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
                                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Icon")
                            })
                        }
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        MyApp()
                    }
                }
                // A surface container using the 'background' color from the theme

            }
        }
    }
}

@Composable
fun MyApp() {
    // The NavController is the central API for the Navigation component.
    // It is stateful and keeps track of the back stack of composables that
    // make up the screens in your app and the state of each screen.

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = "login") {
        composable(route = "login") {
            LoginPage(navController = navHostController)
        }
        // the route contains embedded values passed from the login page.
        composable(route = "jokesPage/{uname}/{pw}") {
            // it = NavBackStackEntry
            // it.arguments = List of values passed from previous page
            var username = it.arguments?.getString("uname")
            var password = it.arguments?.getString("pw")

            // ensure nothing is null
            if(username == null) username = "nobody"
            if(password == null) password = "nothing"
            if(username != null && password != null) {
                JokesPage(navController = navHostController, username, password)
            }
        }
        composable(route = "createJokePage") {
            CreateJokePage(navController = navHostController)
        }
    }
}

