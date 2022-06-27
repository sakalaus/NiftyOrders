package com.pa.niftyorders.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.pa.niftyorders.rememberAppState
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme

@Composable
fun NiftyOrdersApp() {
    NiftyOrdersTheme() {
        val appState = rememberAppState()
        Scaffold(

        ) {

        }
    }
}