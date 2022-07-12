package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pa.niftyorders.R
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.ui.NiftyOrdersAppState
import com.pa.niftyorders.ui.screens.cart.BrandedOutlinedButton
import com.pa.niftyorders.ui.screens.cart.CartQuantity
import com.pa.niftyorders.ui.screens.cart.CartScreen
import com.pa.niftyorders.ui.theme.ThemeElements
import com.pa.niftyorders.utils.CURRENCY_SIGN
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
                doScroll = doScroll,
                onProductInDisplaySelect =  onProductClick
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
fun ProductSectionHeader(
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

@Composable
fun ProductsDisplay(
    topProducts: List<Product>,
    doScroll: (LazyListState, CoroutineScope) -> Unit,
    onProductInDisplaySelect: (Long) -> Unit
) {
    val topProductsLazyRowState = rememberLazyListState()
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
            ProductSectionHeader(
                caption = stringResource(R.string.top_products),
                doScroll = { doScroll(topProductsLazyRowState, scope) },
                arrowIcon = arrowIcon,
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
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
    }
}

@Composable
fun ProductImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = 0.dp
) {
    NiftySurface(
        backgroundColor = Color.LightGray,
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProductFoundation(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    color: Color = ThemeElements.colors.primaryBackgroundColor,
    contentColor: Color = ThemeElements.colors.primaryTextColor,
    border: BorderStroke? = null,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    NiftySurface(
        modifier = modifier,
        shape = shape,
        backgroundColor = color,
        contentColor = contentColor,
        elevation = elevation,
        border = border,
        cornerSize = 8.dp,
        content = content
    )
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
                width = 120.dp,
                height = 170.dp
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
                    .height(90.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                ProductImage(
                    imageUrl = product.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    color = ThemeElements.colors.primaryTextColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "$CURRENCY_SIGN ${product.price.toFloat()}",
                maxLines = 1,
                style = TextStyle(
                    color = ThemeElements.colors.accentColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun AddToCartDialog(
    product: Product,
    cartLine: CartLine,
    onQuantityIncrease: (Long?) -> Unit,
    onQuantityDecrease: (Long?) -> Unit,
    onDismissAddToCart: () -> Unit,
    onAddToCart: (CartLine) -> Unit
) {
    Dialog(onDismissRequest = onDismissAddToCart) {
        Box(modifier = Modifier.padding(0.dp)) {
            Column(
                modifier = Modifier
                    .background(ThemeElements.colors.secondaryBackgroundColor)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Product image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = product.name,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                CartQuantity(cartLine, onQuantityDecrease, onQuantityIncrease)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = product.description,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            BrandedOutlinedButton(text = stringResource(R.string.add_to_cart)){
                onAddToCart(cartLine)
            }
        }
    }
}



