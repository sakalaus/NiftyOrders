package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pa.niftyorders.data.local.entities.Product
import com.pa.niftyorders.ui.NiftyOrdersAppState
import com.pa.niftyorders.ui.theme.ThemeElements

@Composable
fun ShopWindowScreen(){
    Text("Shop window screen")
}

@Composable
fun ShopWindowScreenWithCartScreen(
    uiState: ShopWindowState,
    appState: NiftyOrdersAppState
){
    Text("Shop window screen with cart")
    ProductsDisplay(
        topProducts = uiState.topProducts
    )
}

@Composable
fun CartScreen(){
    Text("Cart screen")
}

@Composable
fun ProductsDisplay(

    topProducts: List<Product>
){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        item {
            LazyRow(modifier = Modifier.fillMaxWidth()){
                itemsIndexed(items = topProducts, key = {_, product -> product.id}){ index, product ->
                    ProductCard(
                        index = index,
                        product = product,
                        onProductClick = {}
                    )
                }
            }
        }
    }
}

@Composable fun ProductImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    ProductSurface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable fun ProductFoundation(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Surface(
        modifier = modifier,
        content = content)
}

@Composable fun ProductSurface(
    color: Color,
    elevation: Dp,
    shape: Shape,
    modifier: Modifier,
    content: @Composable () -> Unit
){

}

@Composable
private fun ProductCard(
    product: Product,
    onProductClick: (Long) -> Unit,
    index: Int,
    modifier: Modifier = Modifier
) {
    ProductFoundation(
        modifier = modifier
            .size(
                width = 170.dp,
                height = 250.dp
            )
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onProductClick(product.id) })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {
                val gradientOffset = 0.4f
                val gradient = 0f
                val gradientWidth = 0f
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                ProductImage(
                    imageUrl = product.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = ThemeElements.colors.secondaryTintColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.description,
                style = MaterialTheme.typography.body1,
                color = ThemeElements.colors.hintColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

