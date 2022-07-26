package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.use_cases.OrderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class ShopWindowViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases
) : ViewModel() {

    var uiState by mutableStateOf(ShopWindowState())
        private set

    init {
        viewModelScope.launch {
            val topProducts = async { orderUseCases.getTopProducts() }
            val promotions = async { orderUseCases.getPromotions() }
            val featuredProductGroups = async { orderUseCases.getFeaturedProductGroups() }
            val deferredCartData  = async { refreshCartData(orderUseCases)  }

            uiState = uiState.copy(
                topProducts = topProducts.await(),
                featuredProductGroups = featuredProductGroups.await(),
                products = topProducts.await(),
                promotions = promotions.await(),
                productsInCart = deferredCartData.await().first,
                cartTotal  = deferredCartData.await().second
            )

            if (uiState.featuredProductGroups.isNotEmpty() && uiState.selectedFeaturedProductGroupId == null) {
                uiState =
                    uiState.copy(selectedFeaturedProductGroupId = uiState.featuredProductGroups.first().id)
                selectFeaturedProductGroup(uiState.selectedFeaturedProductGroupId!!)
            }

        }

        //createDemoData()

    }

    private suspend fun refreshCartData(getProductsInCart: OrderUseCases): Pair<List<CartLine>, BigDecimal> {
        val productsInCart = orderUseCases.getProductsInCart()
        val productsTotalPrice = productsInCart.fold(BigDecimal(0)) { initial, item ->
            initial + item.totalPrice
        }
        return Pair(productsInCart, productsTotalPrice)
    }

    fun onEvent(event: ShopWindowEvent) {
        when (event) {
            is ShopWindowEvent.ProductRowScroll -> doScroll(
                event.lazyListState,
                event.coroutineScope
            )
            is ShopWindowEvent.ProductInCartSelect -> TODO()
            is ShopWindowEvent.CartQuantityIncrease -> changeQuantityInCart(
                cartLineId = event.cartLineId,
                changeBy = 1
            )
            is ShopWindowEvent.CartQuantityDecrease -> changeQuantityInCart(
                cartLineId = event.cartLineId,
                changeBy = -1
            )
            is ShopWindowEvent.ProductInDisplaySelect -> startAddToCartDialog(
                productId = event.productId
            )
            is ShopWindowEvent.FeaturedProductGroupSelect -> selectFeaturedProductGroup(
                productGroupId = event.productGroupId
            )
            is ShopWindowEvent.ProductAddToCart -> addProductToCart(
                cartLine = event.cartLine
            )
            ShopWindowEvent.DemoDataCreation -> createDemoData()
            ShopWindowEvent.AddToCartDismiss -> dismissAddToCartDialog()
        }
    }

    private fun selectFeaturedProductGroup(productGroupId: Long) {
        viewModelScope.launch {
            uiState = uiState.copy(selectedFeaturedProductGroupId = productGroupId)
            uiState.selectedFeaturedProductGroupId?.let {
                uiState =
                    uiState.copy(productsInFeaturedGroup = orderUseCases.getProductsInGroup(it))
            }
        }
    }

    private fun startAddToCartDialog(productId: Long) {
        val selectedProduct = uiState.products.first { it.id == productId }
        val pendingCartLine = CartLine(
            name = selectedProduct.name,
            productId = selectedProduct.id,
            imageUrl = selectedProduct.imageUrl,
            quantity = 1f,
            price = selectedProduct.price,
            totalPrice = selectedProduct.price
        )
        uiState = uiState.copy(
            isAddToCartDialogOpen = true,
            selectedProduct = selectedProduct,
            pendingCartLine = pendingCartLine
        )
    }

    private fun dismissAddToCartDialog() {
        uiState = uiState.copy(
            isAddToCartDialogOpen = false,
            selectedProduct = null,
            pendingCartLine = null
        )
    }

    private fun addProductToCart(cartLine: CartLine) {
        viewModelScope.launch {
            orderUseCases.addProductToCart(cartLine)
            val (productsInCart, productsTotalPrice) = refreshCartData(orderUseCases)
            uiState = uiState.copy(productsInCart = productsInCart, cartTotal = productsTotalPrice)
            dismissAddToCartDialog()
        }
    }

    private fun changeQuantityInCart(cartLineId: Long?, changeBy: Int) {
        // cartLineId == -1L - the line has not been added to cart
        // no need to update DB, only state is updated
        if (cartLineId == null) {
            uiState = uiState.copy(
                pendingCartLine = uiState.pendingCartLine!!.copy(
                    quantity = uiState.pendingCartLine!!.quantity + changeBy,
                    totalPrice = uiState.pendingCartLine!!.totalPrice + BigDecimal(changeBy) * uiState.pendingCartLine!!.price
                )
            )
        } else {
            viewModelScope.launch {
                orderUseCases.changeQuantityInCart(
                    uiState.productsInCart,
                    cartLineId = cartLineId,
                    changeQuantityBy = changeBy
                )
                val (productsInCart, productsTotalPrice) = refreshCartData(orderUseCases)
                uiState =
                    uiState.copy(productsInCart = productsInCart, cartTotal = productsTotalPrice)
            }
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