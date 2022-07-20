package com.pa.niftyorders.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pa.niftyorders.R
import com.pa.niftyorders.ui.screens.shopwindow.ShopWindowRoute
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme
import com.pa.niftyorders.ui.theme.ThemeElements
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
            windowSizeClass = windowSizeClass
        )

        val isExpandedScreen = windowSizeClass == WindowSizeClass.Expanded

        Scaffold(
            scaffoldState = appState.scaffoldState,
            drawerGesturesEnabled = true,
            bottomBar = {
                BottomAppBar(backgroundColor = ThemeElements.colors.primaryBackgroundColor) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Search, stringResource(R.string.search))
                    }
                    Spacer(Modifier.weight(1f, true))
                    if (!isExpandedScreen){
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.ShoppingCart, stringResource(R.string.cart))
                        }
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Settings, stringResource(R.string.settings))
                    }
                }
            }
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = Tabs.SHOP.route
            ) {
                composable(
                    route = Tabs.SHOP.route
                ) {
                    ShopWindowRoute(
                        appState = appState,
                        isExpandedScreen = isExpandedScreen
                    )
                }
            }
        }
    }
}

