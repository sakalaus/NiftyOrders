package com.pa.niftyorders.ui.components.promotioncard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.niftyorders.domain.model.entities.Promotion
import com.pa.niftyorders.ui.components.productcard.ProductFoundation
import com.pa.niftyorders.ui.components.productcard.ProductImage
import com.pa.niftyorders.ui.theme.ThemeElements
import com.pa.niftyorders.utils.CURRENCY_SIGN

@Composable
fun PromotionCard(
    promotion: Promotion,
    onProductClick: (Long) -> Unit,
    index: Int,
    modifier: Modifier = Modifier
) {
    ProductFoundation(
        modifier = modifier
            .size(
                width = 170.dp,
                height = 120.dp
            )
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onProductClick(promotion.productId) })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ProductImage(
                    imageUrl = promotion.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = promotion.name,
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
                text = "$CURRENCY_SIGN ${promotion.price.toFloat()}",
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