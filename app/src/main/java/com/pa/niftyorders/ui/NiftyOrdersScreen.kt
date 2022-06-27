package com.pa.niftyorders.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pa.niftyorders.ui.screens.shopwindow.ShopWindowScreen
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme

@Composable
fun NiftyOrdersScreen() {
    NiftyOrdersTheme() {
        val appState = rememberAppState()
        Scaffold(
            scaffoldState = appState.scaffoldState
        ) {
            paddingValues ->
            NavHost(
                navController = appState.navController,
                startDestination = NavTree.ShopWindow.name){
                composable(NavTree.ShopWindow.name) { ShopWindowScreen() }
            }
        }
    }
}