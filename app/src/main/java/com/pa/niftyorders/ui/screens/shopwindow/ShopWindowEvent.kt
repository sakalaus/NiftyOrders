package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.lazy.LazyListState
import kotlinx.coroutines.CoroutineScope

sealed class ShopWindowEvent() {
    data class ProductRowScroll(val lazyListState: LazyListState, val coroutineScope: CoroutineScope): ShopWindowEvent()
    data class  ProductInDisplaySelection(val productId: Long): ShopWindowEvent()
    data class  ProductInCartSelection(val productId: Long): ShopWindowEvent()
    data class  CartQuantityIncrease(val cartLineId: Long): ShopWindowEvent()
    data class  CartQuantityDecrease(val cartLineId: Long): ShopWindowEvent()
    object DemoDataCreation: ShopWindowEvent()
}