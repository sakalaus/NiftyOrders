package com.pa.niftyorders.ui.screens.shopwindow

import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import java.math.BigDecimal

data class ShopWindowState(
    val isCartOpen: Boolean = false,
    val products: List<Product> = emptyList(),
    val cartTotal: BigDecimal = BigDecimal(0),
    val topProducts: List<Product> = emptyList(),
    val recommendedProducts: List<Product> = emptyList(),
    val featuredGroups: List<Product> = emptyList(),
    val productsInCart: List<CartLine> = emptyList(),
    val addToCartDialogOpen: Boolean = false,
    val selectedProduct: Product? = null
) {
}

