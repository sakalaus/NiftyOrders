package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.lazy.LazyListState
import kotlinx.coroutines.CoroutineScope

sealed class ShopWindowEvent() {
    data class ProductRowScroll(val lazyListState: LazyListState, val coroutineScope: CoroutineScope): ShopWindowEvent()
    data class  CartProductSelection(val productId: Long): ShopWindowEvent()
    data class  CartQuantityDecrease(val productId: Long): ShopWindowEvent()
    data class  CartQuantityIncrease(val productId: Long): ShopWindowEvent()

}