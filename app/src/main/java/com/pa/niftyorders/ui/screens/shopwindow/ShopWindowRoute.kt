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
    isExpandedScreen: Boolean
) {

    val uiState = viewModel.uiState


    ShopWindowRoute(
        uiState = uiState,
        appState = appState,
        isExpandedScreen = isExpandedScreen,
        doScroll = { lazyListState, coroutineScope ->
            viewModel.onEvent(
                ShopWindowEvent.ProductRowScroll(
                    lazyListState = lazyListState,
                    coroutineScope = coroutineScope
                )
            )
        },
        onProductClick = { productId ->
            viewModel.onEvent(
                ShopWindowEvent.CartProductSelection(
                    productId = productId
                )
            )
        },
        onQuantityIncrease = { productId ->
            viewModel.onEvent(
                ShopWindowEvent.CartQuantityIncrease(
                    productId = productId
                )
            )
        },
        onQuantityDecrease = { productId ->
            viewModel.onEvent(
                ShopWindowEvent.CartQuantityDecrease(
                    productId = productId
                )
            )
        },
    )
}

@Composable
private fun ShopWindowRoute(
    uiState: ShopWindowState,
    appState: NiftyOrdersAppState,
    isExpandedScreen: Boolean,
    doScroll: (LazyListState, CoroutineScope) -> Unit,
    onProductClick: (Long) -> Unit,
    onQuantityIncrease: (Long) -> Unit,
    onQuantityDecrease: (Long) -> Unit
) {
    val shopWindowScreenType = getScreenType(
        isExpandedScreen = isExpandedScreen,
        uiState = uiState
    )
    when (shopWindowScreenType) {
        ShopWindowScreenType.ShopWindowScreenWithCart -> ShopWindowScreenWithCartScreen(
            appState = appState,
            doScroll = doScroll,
            uiState = uiState,
            onProductClick = onProductClick,
            onQuantityIncrease = onQuantityIncrease,
            onQuantityDecrease = onQuantityDecrease
        )
        ShopWindowScreenType.ShopWindowScreen -> ShopWindowScreen()
        ShopWindowScreenType.CartScreen -> CartScreen(
            productsInCart = uiState.productsInCart,
            onProductClick = onProductClick,
            onQuantityIncrease = onQuantityIncrease,
            onQuantityDecrease = onQuantityDecrease
        )
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