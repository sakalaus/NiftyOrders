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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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
            val topProducts = orderUseCases.getTopProducts()
            uiState = uiState.copy(topProducts = topProducts)
            uiState = uiState.copy(products = topProducts) // TODO Change
        }
        viewModelScope.launch {
            val (productsInCart, productsTotalPrice) = refreshCartData(orderUseCases)
            uiState = uiState.copy(productsInCart = productsInCart, cartTotal = productsTotalPrice)
        }
        viewModelScope.launch {
            val promotions = orderUseCases.getPromotions()
            uiState = uiState.copy(promotions = promotions)
        }
        viewModelScope.launch {
            val featuredProductGroups = orderUseCases.getFeaturedProductGroups()
            uiState = uiState.copy(featuredProductGroups = featuredProductGroups)
        }
        if (uiState.topProducts.isEmpty()) {
            createDemoData()
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
        uiState = uiState.copy(selectedFeaturedProductGroupId = productGroupId)
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
            addToCartDialogOpen = true,
            selectedProduct = selectedProduct,
            pendingCartLine = pendingCartLine
        )
    }

    private fun dismissAddToCartDialog() {
        uiState = uiState.copy(
            addToCartDialogOpen = false,
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