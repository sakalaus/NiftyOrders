package com.pa.niftyorders.ui.screens.shopwindow

import com.pa.niftyorders.data.local.entities.Product

data class ShopWindowState(
    val isCartOpen: Boolean = false,
    val products: List<Product> = emptyList(),
    val topProducts: List<Product> = emptyList(),
    val recommendedProducts: List<Product> = emptyList(),
    val featuredGroups: List<Product> = emptyList()
) {
}