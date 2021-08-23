package com.example.rickandmortycompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DeepSpaceSparkle,
    primaryVariant = Beige,
    secondary = CadetBlue,
    background = PowderBlue,
    error = Beige


)

private val LightColorPalette = lightColors(
    primary = DeepSpaceSparkle,
    primaryVariant = DeepSpaceSparkle,
    secondary = CadetBlue,
    background = PowderBlue,
    error = Beige
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
fun RickAndMortyComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
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