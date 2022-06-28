package com.pa.niftyorders.ui

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pa.niftyorders.utils.WindowSizeClass
import kotlinx.coroutines.CoroutineScope

enum class NavTree{
    ShopWindow
}

class NiftyOrdersAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass,
    private val resources: Resources,
    coroutineScope: CoroutineScope
)

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    windowSizeClass: WindowSizeClass,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
        remember{
            NiftyOrdersAppState(scaffoldState, navController, windowSizeClass, resources, coroutineScope)
        }


@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
