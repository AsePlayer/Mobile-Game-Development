package com.ryan.jokesappwithnavigation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = YellowDark,
    primaryVariant = YellowSortaDark,
    secondary = YellowLightest
)

private val LightColorPalette = lightColors(
    primary = YellowDark,
    primaryVariant = YellowSortaDark,
    secondary = YellowLightest

//  val YellowDark = Color(0xFFDB19)
//  val YellowSortaDark = Color(0xB29911)
//  val YellowLight = Color(0xFFDB19)
//  val YellowLightest = Color(0xFFF4BA)
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun JokesAppWithNavigationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}