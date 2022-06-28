package com.pa.niftyorders.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pa.niftyorders.ui.screens.shopwindow.ShopWindowScreen
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme
import com.pa.niftyorders.utils.WindowSizeClass

@Composable
fun NiftyOrdersScreen(windowSizeClass: WindowSizeClass) {
    NiftyOrdersTheme() {

        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
        }

        val appState = rememberAppState(
            windowSizeClass = windowSizeClass)

        Scaffold(
            scaffoldState = appState.scaffoldState
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = NavTree.ShopWindow.name){
                composable(NavTree.ShopWindow.name) { ShopWindowScreen() }
            }
        }
    }
}