package com.pa.niftyorders.ui

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pa.niftyorders.R
import com.pa.niftyorders.utils.WindowSizeClass
import kotlinx.coroutines.CoroutineScope

enum class NavTree {
    ShopWindow
}

enum class Tabs(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    SHOP(R.string.shop, Icons.Outlined.Home, "home/shop"),
    SEARCH(R.string.search, Icons.Outlined.Search, "home/search"),
    CART(R.string.cart, Icons.Outlined.ShoppingCart, "home/cart"),
    PROFILE(R.string.profile, Icons.Outlined.AccountCircle, "home/profile")
}

class NiftyOrdersAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass,
    private val resources: Resources,
    coroutineScope: CoroutineScope
){
    val tabs = Tabs.values()
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    windowSizeClass: WindowSizeClass,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember {
        NiftyOrdersAppState(
            scaffoldState = scaffoldState,
            navController = navController,
            windowSizeClass = windowSizeClass,
            resources = resources,
            coroutineScope = coroutineScope
        )
    }


@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
