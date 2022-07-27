package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pa.niftyorders.R
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.model.entities.Promotion
import com.pa.niftyorders.ui.NiftyOrdersAppState
import com.pa.niftyorders.ui.screens.cart.CartScreen
import com.pa.niftyorders.ui.components.featuredgroups.FeaturedProductGroups
import com.pa.niftyorders.ui.components.productcard.ProductCard
import com.pa.niftyorders.ui.components.promotioncard.PromotionCard
import com.pa.niftyorders.ui.components.addtocart.AddToCartDialog
import com.pa.niftyorders.ui.components.basic.NiftySurface
import com.pa.niftyorders.ui.components.productgrid.ProductGrid
import com.pa.niftyorders.ui.components.productsDisplay.ProductsDisplay
import com.pa.niftyorders.ui.theme.ThemeElements
import kotlinx.coroutines.CoroutineScope

@Composable
fun ShopWindowScreen(
    uiState: ShopWindowState,
    appState: NiftyOrdersAppState,
    doScroll: (LazyListState, CoroutineScope) -> Unit,
    onProductClick: (Long) -> Unit,
    onQuantityIncrease: (Long?) -> Unit,
    onQuantityDecrease: (Long?) -> Unit,
    onFeaturedProductGroupSelect: (Long) -> Unit,
    onAddToCart: (CartLine) -> Unit,
    onDismissAddToCart: () -> Unit,
    onSearchFieldValueChange: (String) -> Unit,
    onSearchFieldFocusChange: (FocusState) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    if (uiState.isAddToCartDialogOpen) {
        AddToCartDialog(
            product = uiState.selectedProduct!!,
            cartLine = uiState.pendingCartLine!!,
            onQuantityIncrease = onQuantityIncrease,
            onQuantityDecrease = onQuantityDecrease,
            onAddToCart = onAddToCart,
            onDismissAddToCart = onDismissAddToCart
        )
    }
    NiftySurface(
        backgroundColor = ThemeElements.colors.secondaryBackgroundColor,
        elevation = 2.dp,
        shape = RectangleShape,
        modifier = Modifier.fillMaxSize()
    ) {
        ProductsDisplay(
            showSearchField = uiState.showSearchField,
            showHorizontalHeaders = uiState.showHorizontalHeaders,
            showTopProducts = uiState.showTopProducts,
            showPromotions = uiState.showPromotions,
            searchField = uiState.searchField,
            topProducts = uiState.topProducts,
            productsInFeaturedGroup = uiState.productsInFeaturedGroup,
            promotions = uiState.promotions,
            featuredProductGroups = uiState.featuredProductGroups,
            selectedFeaturedProductGroupId = uiState.selectedFeaturedProductGroupId,
            doScroll = doScroll,
            onProductInDisplaySelect = onProductClick,
            onFeaturedProductGroupSelect = onFeaturedProductGroupSelect,
            onSearchFieldValueChange = onSearchFieldValueChange,
            onSearchFieldFocusChange = onSearchFieldFocusChange
        )
    }

}

@Composable
fun ShopWindowScreenWithCartScreen(
    uiState: ShopWindowState,
    doScroll: (LazyListState, CoroutineScope) -> Unit,
    onProductClick: (Long) -> Unit,
    onQuantityIncrease: (Long?) -> Unit,
    onQuantityDecrease: (Long?) -> Unit,
    onFeaturedProductGroupSelect: (Long) -> Unit,
    onAddToCart: (CartLine) -> Unit,
    onDismissAddToCart: () -> Unit,
    onSearchFieldValueChange: (String) -> Unit,
    onSearchFieldFocusChange: (FocusState) -> Unit
) {
    if (uiState.isAddToCartDialogOpen) {
        AddToCartDialog(
            product = uiState.selectedProduct!!,
            cartLine = uiState.pendingCartLine!!,
            onQuantityIncrease = onQuantityIncrease,
            onQuantityDecrease = onQuantityDecrease,
            onAddToCart = onAddToCart,
            onDismissAddToCart = onDismissAddToCart
        )
    }
    Row(modifier = Modifier.fillMaxSize()) {
        NiftySurface(
            backgroundColor = ThemeElements.colors.secondaryBackgroundColor,
            elevation = 2.dp,
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            ProductsDisplay(
                showSearchField = uiState.showSearchField,
                showHorizontalHeaders = uiState.showHorizontalHeaders,
                showTopProducts = uiState.showTopProducts,
                showPromotions = uiState.showPromotions,
                searchField = uiState.searchField,
                topProducts = uiState.topProducts,
                productsInFeaturedGroup = uiState.productsInFeaturedGroup,
                promotions = uiState.promotions,
                featuredProductGroups = uiState.featuredProductGroups,
                selectedFeaturedProductGroupId = uiState.selectedFeaturedProductGroupId,
                doScroll = doScroll,
                onProductInDisplaySelect = onProductClick,
                onFeaturedProductGroupSelect = onFeaturedProductGroupSelect,
                onSearchFieldValueChange = onSearchFieldValueChange,
                onSearchFieldFocusChange = onSearchFieldFocusChange
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        NiftySurface(
            backgroundColor = ThemeElements.colors.secondaryBackgroundColor,
            elevation = 2.dp,
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth()
        ) {
            CartScreen(
                productsInCart = uiState.productsInCart,
                cartTotal = uiState.cartTotal,
                onProductClick = onProductClick,
                onQuantityIncrease = onQuantityIncrease,
                onQuantityDecrease = onQuantityDecrease
            )
        }
    }
}




