package com.pa.niftyorders.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme
import com.pa.niftyorders.ui.theme.ThemeElements

@Composable
fun NiftyOrdersApp(){
    NiftyOrdersTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = ThemeElements.colors.primaryBackgroundColor
        ) {
            Text(text = "Hello, Nifty")
        }
    }
}