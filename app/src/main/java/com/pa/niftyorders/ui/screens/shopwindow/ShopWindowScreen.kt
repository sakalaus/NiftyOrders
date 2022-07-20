package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
import com.pa.niftyorders.ui.components.productgrid.ProductGrid
import com.pa.niftyorders.ui.theme.ThemeElements
import kotlinx.coroutines.CoroutineScope

@Composable
fun ShopWindowScreen() {
    Text("Shop window screen")
}

@Composable
fun ShopWindowScreenWithCartScreen(
    uiState: ShopWindowState,
    appState: NiftyOrdersAppState,
    doScroll: (LazyListState, CoroutineScope) -> Unit,
    onProductClick: (Long) -> Unit,
    onQuantityIncrease: (Long?) -> Unit,
    onQuantityDecrease: (Long?) -> Unit,
    onProductInDisplaySelect: (Long) -> Unit,
    onFeaturedProductGroupSelect: (Long) -> Unit,
    onAddToCart: (CartLine) -> Unit,
    onDismissAddToCart: () -> Unit
) {
    if (uiState.addToCartDialogOpen) {
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
                topProducts = uiState.topProducts,
                productsInFeaturedGroup = uiState.productsInFeaturedGroup,
                promotions = uiState.promotions,
                featuredProductGroups = uiState.featuredProductGroups,
                selectedFeaturedProductGroupId = uiState.selectedFeaturedProductGroupId,
                doScroll = doScroll,
                onProductInDisplaySelect = onProductClick,
                onFeaturedProductGroupSelect = onFeaturedProductGroupSelect
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

@Composable
fun HorizontalSectionHeader(
    caption: String,
    doScroll: () -> Unit,
    arrowIcon: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .heightIn(min = 24.dp, max = 28.dp)
            .then(Modifier.padding(start = 24.dp, top = 0.dp))
    ) {
        Text(
            text = caption,
            style = MaterialTheme.typography.h6,
            color = ThemeElements.colors.primaryTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start)
        )
        IconButton(
            onClick = {
                doScroll()
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = arrowIcon,
                tint = ThemeElements.colors.primaryTintColor,
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsDisplay(
    topProducts: List<Product>,
    featuredProductGroups: List<ProductGroup>,
    productsInFeaturedGroup: List<Product>,
    selectedFeaturedProductGroupId: Long?,
    promotions: List<Promotion>,
    doScroll: (LazyListState, CoroutineScope) -> Unit,
    onProductInDisplaySelect: (Long) -> Unit,
    onFeaturedProductGroupSelect: (Long) -> Unit
) {
    val topProductsLazyRowState = rememberLazyListState()
    val promotionsLazyRowState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val arrowIcon by remember(topProductsLazyRowState.firstVisibleItemIndex) {
        derivedStateOf {
            if (topProductsLazyRowState.firstVisibleItemIndex == 0) Icons.Outlined.ArrowForward else Icons.Outlined.ArrowBack
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            HorizontalSectionHeader(
                caption = stringResource(R.string.top_products),
                doScroll = { doScroll(topProductsLazyRowState, scope) },
                arrowIcon = arrowIcon,
            )
            LazyRow(
                modifier = Modifier.fillParentMaxWidth(),
                state = topProductsLazyRowState
            ) {
                itemsIndexed(
                    items = topProducts,
                    key = { _, product -> product.id }) { index, product ->
                    ProductCard(
                        modifier = Modifier.padding(4.dp),
                        index = index,
                        product = product,
                        onProductClick = onProductInDisplaySelect
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(2.dp))
        }
        item {
            HorizontalSectionHeader(
                caption = stringResource(R.string.promotions),
                doScroll = { doScroll(promotionsLazyRowState, scope) },
                arrowIcon = arrowIcon,
            )
            LazyRow(
                modifier = Modifier.fillParentMaxWidth(),
                state = promotionsLazyRowState
            ) {
                itemsIndexed(
                    items = promotions,
                    key = { _, promotion -> promotion.id }) { index, promotion ->
                    PromotionCard(
                        modifier = Modifier.padding(4.dp),
                        index = index,
                        promotion = promotion,
                        onProductClick = onProductInDisplaySelect
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                item {
                    FeaturedProductGroups(
                        featuredProductGroups,
                        selectedFeaturedProductGroupId,
                        onFeaturedProductGroupSelect
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            ProductGrid(
                modifier = Modifier.fillParentMaxWidth().fillParentMaxHeight(),
                productsInFeaturedGroup = productsInFeaturedGroup,
                onProductInDisplaySelect = onProductInDisplaySelect)
        }
    }
}

@Composable
fun NiftySurface(
    backgroundColor: Color,
    elevation: Dp,
    shape: Shape,
    contentColor: Color = ThemeElements.colors.secondaryTintColor,
    contentAlignment: Alignment = Alignment.TopStart,
    border: BorderStroke? = null,
    cornerSize: Dp = 0.dp,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = false)
            .zIndex(elevation.value)
            .clip(RoundedCornerShape(cornerSize))
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .background(
                color = backgroundColor,
                shape = shape
            ),
        contentAlignment = contentAlignment
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)
    }
}

