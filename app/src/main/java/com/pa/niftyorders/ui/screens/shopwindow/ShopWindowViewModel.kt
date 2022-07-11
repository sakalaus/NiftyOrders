package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pa.niftyorders.domain.use_cases.OrderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopWindowViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases
) : ViewModel() {

    var uiState by mutableStateOf(ShopWindowState())
        private set

    init {
        viewModelScope.launch {
            val topProducts = orderUseCases.getTopProducts()
            uiState = uiState.copy(topProducts = topProducts)
        }
        viewModelScope.launch {
            val productsInCart = orderUseCases.getProductsInCart()
            uiState = uiState.copy(productsInCart = productsInCart)
        }
    }

    fun onEvent(event: ShopWindowEvent) {
        when (event) {
            is ShopWindowEvent.ProductRowScroll -> doScroll(
                event.lazyListState,
                event.coroutineScope
            )
            is ShopWindowEvent.CartProductSelection -> TODO()
            is ShopWindowEvent.CartQuantityIncrease -> changeQuantityInCart(
                cartLineId = event.cartLineId,
                changeBy = 1
            )
            is ShopWindowEvent.CartQuantityDecrease -> changeQuantityInCart(
                cartLineId = event.cartLineId,
                changeBy = -1
            )
            ShopWindowEvent.DemoDataCreation -> createDemoData()
        }
    }

    private fun changeQuantityInCart(cartLineId: Long, changeBy: Int) {
        viewModelScope.launch {
            orderUseCases.changeQuantityInCart(cartLineId = cartLineId, changeBy = changeBy)
            uiState = uiState.copy(productsInCart = orderUseCases.getProductsInCart())
        }
    }

    private val doScroll: (
        lazyListState: LazyListState,
        scope: CoroutineScope
    ) -> Unit = { state, scope ->
        scope.launch {
            state.animateScrollToItem(
                if (state.firstVisibleItemIndex == 0) state.layoutInfo.totalItemsCount - 1 else 0
            )
        }
    }

    private fun createDemoData() {
        viewModelScope.launch {
            orderUseCases.createDemoData()
        }
    }

}