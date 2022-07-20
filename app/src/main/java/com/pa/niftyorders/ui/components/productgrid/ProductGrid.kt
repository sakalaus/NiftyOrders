package com.pa.niftyorders.ui.components.productgrid

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.ui.components.productcard.ProductCard

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun ProductGrid(
    modifier: Modifier,
    productsInFeaturedGroup: List<Product>,
    onProductInDisplaySelect: (Long) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        cells = GridCells.Adaptive(minSize = 130.dp)
    ) {
        items(count = productsInFeaturedGroup.size) { index ->
            ProductCard(
                modifier = Modifier.padding(4.dp),
                index = index,
                product = productsInFeaturedGroup[index],
                onProductClick = onProductInDisplaySelect
            )
        }
    }
}