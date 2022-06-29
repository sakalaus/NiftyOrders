package com.pa.niftyorders.ui.screens.shopwindow

import com.pa.niftyorders.data.local.entities.Product
import java.math.BigDecimal

data class ShopWindowState(
    val isCartOpen: Boolean = false,
    val products: List<Product> = emptyList(),
    val topProducts: List<Product> = emptyList(),
    val recommendedProducts: List<Product> = emptyList(),
    val featuredGroups: List<Product> = emptyList(),
    val productsInCart: List<OrderLine> = emptyList()
) {
}

data class OrderLine(
    val product: Product,
    val quantity: Float,
    val price: BigDecimal,
    val totalPrice: BigDecimal
)