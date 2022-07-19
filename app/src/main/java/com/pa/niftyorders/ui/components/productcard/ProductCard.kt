package com.pa.niftyorders.ui.components.productcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.ui.screens.shopwindow.NiftySurface
import com.pa.niftyorders.ui.theme.ThemeElements
import com.pa.niftyorders.utils.CURRENCY_SIGN


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
fun ProductCard(
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