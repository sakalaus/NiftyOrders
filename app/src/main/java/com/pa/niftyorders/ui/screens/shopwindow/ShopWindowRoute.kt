package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ShopWindowRoute(
    navController: NavController,
    viewModel: ShopWindowViewModel = hiltViewModel(),
    isExpandedScreen: Boolean
){
    val uiState = viewModel.uiState

   ShopWindowRoute(
       uiState = uiState,
       isExpandedScreen = isExpandedScreen
   )
}

@Composable
private fun ShopWindowRoute(
    uiState: ShopWindowState,
    isExpandedScreen: Boolean
){
    val shopWindowScreenType = getScreenType(
        isExpandedScreen = isExpandedScreen,
        uiState = uiState
    )
    when (shopWindowScreenType){
        ShopWindowScreenType.ShopWindowScreenWithCart -> ShopWindowScreenWithCartScreen()
        ShopWindowScreenType.ShopWindowScreen -> ShopWindowScreen()
        ShopWindowScreenType.CartScreen -> CartScreen()
    }
}

private enum class ShopWindowScreenType {
    ShopWindowScreen,
    ShopWindowScreenWithCart,
    CartScreen
}

private fun getScreenType(isExpandedScreen: Boolean, uiState: ShopWindowState): ShopWindowScreenType{
    return when (isExpandedScreen){
        true -> ShopWindowScreenType.ShopWindowScreenWithCart
        false -> {if (uiState.isCartOpen) ShopWindowScreenType.CartScreen else ShopWindowScreenType.ShopWindowScreen}
    }
}