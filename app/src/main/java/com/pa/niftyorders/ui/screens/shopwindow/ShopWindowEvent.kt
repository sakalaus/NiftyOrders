package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.lazy.LazyListState
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.ProductGroup
import kotlinx.coroutines.CoroutineScope

sealed class ShopWindowEvent() {
    data class ProductRowScroll(val lazyListState: LazyListState, val coroutineScope: CoroutineScope): ShopWindowEvent()
    data class  ProductInDisplaySelect(val productId: Long): ShopWindowEvent()
    data class FeaturedProductGroupSelect(val productGroupId: Long): ShopWindowEvent()
    data class ProductAddToCart(val cartLine: CartLine): ShopWindowEvent()
    data class  ProductInCartSelect(val productId: Long): ShopWindowEvent()
    data class  CartQuantityIncrease(val cartLineId: Long?): ShopWindowEvent()
    data class  CartQuantityDecrease(val cartLineId: Long?): ShopWindowEvent()
    object ToggleCartOpen: ShopWindowEvent()
    object DemoDataCreation: ShopWindowEvent()
    object AddToCartDismiss: ShopWindowEvent()
}