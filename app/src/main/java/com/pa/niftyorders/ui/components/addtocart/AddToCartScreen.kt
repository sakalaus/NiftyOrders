package com.pa.niftyorders.ui.components.addtocart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pa.niftyorders.R
import com.pa.niftyorders.data.repository_mock.sampleCart
import com.pa.niftyorders.data.repository_mock.sampleProducts
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.ui.screens.cart.CartQuantityPicker
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme
import com.pa.niftyorders.ui.theme.ThemeElements

@Composable
fun AddToCartDialog(
    product: Product,
    cartLine: CartLine,
    onQuantityIncrease: (Long?) -> Unit,
    onQuantityDecrease: (Long?) -> Unit,
    onDismissAddToCart: () -> Unit,
    onAddToCart: (CartLine) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val scrollableState = rememberScrollState()

    Dialog(onDismissRequest = onDismissAddToCart) {
        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                AddToCartButton(
                    cartLine = cartLine,
                    onAddToCart = onAddToCart
                )
            }) {
            Box(modifier = Modifier.padding(0.dp)) {
                LazyColumn(
                    modifier = Modifier
                        .background(ThemeElements.colors.primaryBackgroundColor)
                        .padding(12.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillParentMaxHeight(0.7f)
                                .fillParentMaxWidth()
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
                    }
                    item{
                        Row(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = 12.dp, horizontal = 8.dp)
                        ) {
                            Text(
                                text = product.name,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    item{
                        CartQuantityPicker(
                            modifier = Modifier.padding(start = 8.dp),
                            cartLine = cartLine,
                            boxSize = 32.dp,
                            fontSize = 20.sp,
                            boxFontSize = 20.sp,
                            onQuantityIncrease = onQuantityIncrease,
                            onQuantityDecrease = onQuantityDecrease
                        )
                    }
                    item{
                        Row(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = 14.dp, horizontal = 8.dp)
                        ) {
                            Text(
                                modifier = Modifier.background(ThemeElements.colors.primaryBackgroundColor),
                                text = product.description,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun AddToCartButton(
    cartLine: CartLine,
    onAddToCart: (CartLine) -> Unit
) {
    ExtendedFloatingActionButton(
        text = { Text(text = stringResource(R.string.add_to_cart)) },
        onClick = { onAddToCart(cartLine) },
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        backgroundColor = ThemeElements.colors.accentColor,
        contentColor = ThemeElements.colors.buttonCaptionColor,
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(
    name = "default",
    backgroundColor = 0xFFFFF,
    showBackground = true,
    device = Devices.PIXEL_C
)
@Composable
private fun AddToCartDialogPreview() {
    NiftyOrdersTheme() {
        AddToCartDialog(
            product = sampleProducts[0],
            cartLine = sampleCart[0].copy(id = null),
            onQuantityIncrease = {},
            onQuantityDecrease = {},
            onDismissAddToCart = {},
            onAddToCart = {}
        )
    }
}