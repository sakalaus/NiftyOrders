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
import androidx.compose.ui.Alignment.Companion.Center
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pa.niftyorders.R
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.data.repository_mock.sampleCart
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.ui.NiftyOrdersAppState
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme
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
    onQuantityIncrease: (Long) -> Unit,
    onQuantityDecrease: (Long) -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        NiftySurface(
            backgroundColor = ThemeElements.colors.secondaryBackgroundColor,
            elevation = 2.dp,
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            ProductsDisplay(
                topProducts = uiState.topProducts,
                doScroll = doScroll
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
                onProductClick = onProductClick,
                onQuantityIncrease = onQuantityIncrease,
                onQuantityDecrease = onQuantityDecrease
            )
        }
    }
}

@Composable
fun CartScreen(
    productsInCart: List<CartLine>,
    onProductClick: (Long) -> Unit,
    onQuantityIncrease: (Long) -> Unit,
    onQuantityDecrease: (Long) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            CartHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.08f)
            )
            CartItems(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f), productsInCart = productsInCart,
                onProductClick = onProductClick,
                onQuantityIncrease = onQuantityIncrease,
                onQuantityDecrease = onQuantityDecrease
            )
            CartFooter(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
            )
        }
    }
}

@Composable
fun CartItems(
    productsInCart: List<CartLine>,
    modifier: Modifier = Modifier,
    onProductClick: (Long) -> Unit,
    onQuantityIncrease: (Long) -> Unit,
    onQuantityDecrease: (Long) -> Unit
) {
    NiftySurface(
        backgroundColor = ThemeElements.colors.primaryBackgroundColor,
        elevation = 4.dp,
        shape = RectangleShape,
        modifier = modifier
    ) {
        LazyColumn() {
            itemsIndexed(
                items = productsInCart,
                key = { _, orderLine -> orderLine.productId })
            { index: Int, cartLine: CartLine ->
                CartRow(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    index = index,
                    cartLine = cartLine,
                    onProductClick = onProductClick,
                    onQuantityDecrease = onQuantityDecrease,
                    onQuantityIncrease = onQuantityIncrease
                )
            }
        }
    }
}

@Composable
fun CartRow(
    modifier: Modifier = Modifier,
    cartLine: CartLine,
    index: Int,
    onProductClick: (Long) -> Unit,
    onQuantityIncrease: (Long) -> Unit,
    onQuantityDecrease: (Long) -> Unit
) {
    Box(modifier = Modifier.then(Modifier.padding(vertical = 4.dp))) {
        Row(modifier = modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Center) {
                ProductImage(
                    imageUrl = cartLine.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = cartLine.name,
                        color = ThemeElements.colors.primaryTextColor,
                        style = TextStyle(
                            color = ThemeElements.colors.primaryTextColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "$CURRENCY_SIGN ${cartLine.totalPrice.toFloat()}",
                            color = ThemeElements.colors.accentColor,
                            style = TextStyle(
                                color = ThemeElements.colors.accentColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Visible
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ArithmeticBox(
                                modifier = Modifier.size(20.dp),
                                id = cartLine.id,
                                text = "+",
                                onKeyPress = onQuantityIncrease
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = cartLine.quantity.toInt().toString(),
                                style = TextStyle(
                                    color = ThemeElements.colors.secondaryTextColor,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            ArithmeticBox(
                                modifier = Modifier.size(20.dp),
                                id = cartLine.id,
                                text = "-",
                                onKeyPress = onQuantityDecrease
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CartHeader(modifier: Modifier = Modifier) {
    NiftySurface(
        backgroundColor = ThemeElements.colors.primaryBackgroundColor,
        elevation = 4.dp,
        shape = RectangleShape,
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp, top = 24.dp),
            text = stringResource(R.string.your_cart),
            style = MaterialTheme.typography.h6,
            color = ThemeElements.colors.primaryTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CartFooter(modifier: Modifier = Modifier) {
    NiftySurface(
        backgroundColor = ThemeElements.colors.primaryBackgroundColor,
        elevation = 4.dp,
        shape = RectangleShape,
        modifier = modifier
    ) {
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
    doScroll: (LazyListState, CoroutineScope) -> Unit
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
                        onProductClick = {}
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
            contentScale = ContentScale.Crop,
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
fun ArithmeticBox(
    modifier: Modifier,
    text: String,
    id: Long,
    onKeyPress: (Long) -> Unit
) {
    NiftySurface(
        modifier = modifier.then(Modifier.clickable { onKeyPress(id) }),
        backgroundColor = ThemeElements.colors.accentColor,
        contentColor = ThemeElements.colors.primaryBackgroundColor,
        contentAlignment = Center,
        elevation = 4.dp,
        shape = RectangleShape,
        cornerSize = 4.dp
    ) {
        Text(
            text = text,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(
    name = "default",
    backgroundColor = 0xFFFFF,
    showBackground = true,
    heightDp = 24,
    widthDp = 24,
    device = Devices.NEXUS_9
)
@Composable
private fun ArithmeticBoxPreview() {
    NiftyOrdersTheme() {
        ArithmeticBox(
            modifier = Modifier
                .size(16.dp)
                .clickable(onClick = {}),
            id = 1,
            text = "+",
            onKeyPress = {}
        )
    }
}

@Preview(
    name = "default",
    backgroundColor = 0xFFFFF,
    showBackground = true,
    heightDp = 60,
    widthDp = 360,
    device = Devices.NEXUS_9
)
@Composable
private fun CartRowPreview() {
    NiftyOrdersTheme() {
        CartRow(
            cartLine = sampleCart[0],
            index = 1,
            onProductClick = {},
            onQuantityIncrease = {},
            onQuantityDecrease = {}
        )
    }

}


