package com.pa.niftyorders.ui.screens.shopwindow

import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.model.entities.Promotion
import com.pa.niftyorders.domain.use_cases.GetFeaturedProductGroups
import java.math.BigDecimal

data class ShopWindowState(
    val isCartOpen: Boolean = false,
    val products: List<Product> = emptyList(),
    val cartTotal: BigDecimal = BigDecimal(0),
    val topProducts: List<Product> = emptyList(),
    val featuredProductGroups: List<ProductGroup> = emptyList(),
    val selectedFeaturedProductGroupId: Long? = null,
    val productsInFeaturedGroup: List<Product> = emptyList(),
    val promotions: List<Promotion> = emptyList(),
    val recommendedProducts: List<Product> = emptyList(),
    val featuredGroups: List<Product> = emptyList(),
    val productsInCart: List<CartLine> = emptyList(),
    val pendingCartLine: CartLine? = null,
    val addToCartDialogOpen: Boolean = false,
    val selectedProduct: Product? = null
) {
}

