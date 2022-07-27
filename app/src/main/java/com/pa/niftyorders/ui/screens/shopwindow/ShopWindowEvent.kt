package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.focus.FocusState
import com.pa.niftyorders.domain.model.entities.CartLine
import kotlinx.coroutines.CoroutineScope

sealed class ShopWindowEvent() {
    data class ProductRowScroll(val lazyListState: LazyListState, val coroutineScope: CoroutineScope): ShopWindowEvent()
    data class  ProductInDisplaySelect(val productId: Long): ShopWindowEvent()
    data class FeaturedProductGroupSelect(val productGroupId: Long): ShopWindowEvent()
    data class ProductAddToCart(val cartLine: CartLine): ShopWindowEvent()
    data class  ProductInCartSelect(val productId: Long): ShopWindowEvent()
    data class  CartQuantityIncrease(val cartLineId: Long?): ShopWindowEvent()
    data class  CartQuantityDecrease(val cartLineId: Long?): ShopWindowEvent()
    data class SearchFieldValueChange(val value: String): ShopWindowEvent()
    data class SearchFieldFocusChanged(val focusState: FocusState): ShopWindowEvent()
    object DemoDataCreation: ShopWindowEvent()
    object AddToCartDismiss: ShopWindowEvent()
}