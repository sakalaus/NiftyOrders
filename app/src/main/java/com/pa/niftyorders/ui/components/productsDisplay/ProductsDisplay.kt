package com.pa.niftyorders.ui.components.productsDisplay

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pa.niftyorders.R
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.model.entities.Promotion
import com.pa.niftyorders.ui.components.featuredgroups.FeaturedProductGroups
import com.pa.niftyorders.ui.components.horizontalSectionHeader.HorizontalSectionHeader
import com.pa.niftyorders.ui.components.productcard.ProductCard
import com.pa.niftyorders.ui.components.productgrid.ProductGrid
import com.pa.niftyorders.ui.components.promotioncard.PromotionCard
import kotlinx.coroutines.CoroutineScope

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
                modifier = Modifier
                    .fillParentMaxWidth()
                    .fillParentMaxHeight(),
                productsInFeaturedGroup = productsInFeaturedGroup,
                onProductInDisplaySelect = onProductInDisplaySelect
            )
        }
    }
}