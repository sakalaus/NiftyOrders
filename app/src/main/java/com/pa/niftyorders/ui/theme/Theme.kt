package com.pa.niftyorders.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun NiftyOrdersTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalColorProvider provides lightPalette, content = content )
}

object ThemeElements{
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current
}


val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}