package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.ui.res.stringResource
import com.pa.niftyorders.R
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.model.entities.Promotion
import com.pa.niftyorders.ui.components.brandedtextfield.TextFieldState
import com.pa.niftyorders.ui.components.brandedtextfield.TextResource
import java.math.BigDecimal

data class ShopWindowState(
    val showSearchField: Boolean = true,
    val showTopProducts: Boolean = true,
    val showHorizontalHeaders: Boolean = false,
    val showPromotions: Boolean = true,
    val searchField: TextFieldState = TextFieldState(hintResId =  R.string.search),
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
    val isAddToCartDialogOpen: Boolean = false,
    val selectedProduct: Product? = null
) {
}

