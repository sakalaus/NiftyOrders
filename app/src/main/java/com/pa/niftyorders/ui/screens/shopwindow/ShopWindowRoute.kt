package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.pa.niftyorders.ui.NiftyOrdersAppState
import kotlinx.coroutines.CoroutineScope

@Composable
fun ShopWindowRoute(
    viewModel: ShopWindowViewModel = hiltViewModel(),
    appState: NiftyOrdersAppState,
    isExpandedScreen: Boolean,
) {

    val uiState = viewModel.uiState

    ShopWindowRoute(
        uiState = uiState,
        appState = appState,
        doScroll = viewModel.doScroll,
        isExpandedScreen = isExpandedScreen
    )
}

@Composable
private fun ShopWindowRoute(
    uiState: ShopWindowState,
    appState: NiftyOrdersAppState,
    doScroll: (LazyListState, CoroutineScope) -> Unit,
    isExpandedScreen: Boolean
) {
    val shopWindowScreenType = getScreenType(
        isExpandedScreen = isExpandedScreen,
        uiState = uiState
    )
    when (shopWindowScreenType) {
        ShopWindowScreenType.ShopWindowScreenWithCart -> ShopWindowScreenWithCartScreen(
            appState = appState,
            doScroll = doScroll,
            uiState = uiState
        )
        ShopWindowScreenType.ShopWindowScreen -> ShopWindowScreen()
        ShopWindowScreenType.CartScreen -> CartScreen()
    }
}

private enum class ShopWindowScreenType {
    ShopWindowScreen,
    ShopWindowScreenWithCart,
    CartScreen
}

private fun getScreenType(
    isExpandedScreen: Boolean,
    uiState: ShopWindowState
): ShopWindowScreenType {
    return when (isExpandedScreen) {
        true -> ShopWindowScreenType.ShopWindowScreenWithCart
        false -> {
            if (uiState.isCartOpen) ShopWindowScreenType.CartScreen else ShopWindowScreenType.ShopWindowScreen
        }
    }
}