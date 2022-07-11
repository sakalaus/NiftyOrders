package com.pa.niftyorders.ui.screens.cart

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.niftyorders.R
import com.pa.niftyorders.data.repository_mock.sampleCart
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.ui.screens.shopwindow.NiftySurface
import com.pa.niftyorders.ui.screens.shopwindow.ProductImage
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme
import com.pa.niftyorders.ui.theme.ThemeElements
import com.pa.niftyorders.utils.CURRENCY_SIGN

@Composable
fun CartScreen(
    productsInCart: List<CartLine>,
    onProductClick: (Long) -> Unit,
    onQuantityIncrease: (Long) -> Unit,
    onQuantityDecrease: (Long) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
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

@OptIn(ExperimentalAnimationApi::class)
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
                                text = stringResource(R.string.minus_sign),
                                disabled = (cartLine.quantity == 0f),
                                onKeyPress = onQuantityDecrease
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            AnimatedContent(
                                targetState = cartLine.quantity.toInt(),
                                transitionSpec = {
                                    if (targetState > initialState) {
                                        slideInVertically { height -> height } + fadeIn() with
                                                slideOutVertically { height -> -height } + fadeOut()
                                    } else {
                                        slideInVertically { height -> -height } + fadeIn() with
                                                slideOutVertically { height -> height } + fadeOut()
                                    }.using(
                                        SizeTransform(clip = false)
                                    )
                                }
                            ) { targetCount ->
                                Text(
                                    text = targetCount.toString(),
                                    style = TextStyle(
                                        color = ThemeElements.colors.secondaryTextColor,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            ArithmeticBox(
                                modifier = Modifier.size(20.dp),
                                id = cartLine.id,
                                text = stringResource(R.string.plus_sign),
                                onKeyPress = onQuantityIncrease
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
fun ArithmeticBox(
    modifier: Modifier,
    text: String,
    id: Long,
    disabled: Boolean = false,
    onKeyPress: (Long) -> Unit
) {
    NiftySurface(
        modifier = if (disabled) modifier else modifier.then(Modifier.clickable { onKeyPress(id) }),
        backgroundColor = if (disabled) ThemeElements.colors.accentDimmedColor else ThemeElements.colors.accentColor,
        contentColor = ThemeElements.colors.primaryBackgroundColor,
        contentAlignment = Alignment.Center,
        elevation = 4.dp,
        shape = RectangleShape,
        cornerSize = 4.dp
    ) {
        Text(
            text = text,
            color = if (disabled) ThemeElements.colors.buttonCaptionDimmedColor else ThemeElements.colors.buttonCaptionColor,
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
            disabled = true,
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