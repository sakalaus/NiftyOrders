package com.pa.niftyorders.ui.components.featuredgroups

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.niftyorders.data.repository_mock.sampleProductGroups
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme
import com.pa.niftyorders.ui.theme.ThemeElements

@Composable
fun FeaturedProductGroups(
    featuredProductGroups: List<ProductGroup>,
    selectedFeaturedProductGroupId: Long?,
    onFeaturedProductGroupSelect: (Long) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items = featuredProductGroups,
            key = { _, featuredProductGroup -> featuredProductGroup.id }) { index, productGroup ->
            FeaturedProductGroupButton(
                caption = productGroup.name,
                selected = (productGroup.id == selectedFeaturedProductGroupId)
            ) {
                onFeaturedProductGroupSelect(productGroup.id)
            }
        }
    }
}

@Composable
fun FeaturedProductGroupButton(
    caption: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(48.dp)
            .shadow(elevation = 4.dp, RoundedCornerShape(8.dp), clip = false)
            .wrapContentWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) ThemeElements.colors.accentColor else ThemeElements.colors.primaryBackgroundColor)
            .clickable {
                onClick()
            }
            .then(modifier)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            text = caption,
            style = textStyle,
            fontSize = 16.sp,
            color = (if (selected) ThemeElements.colors.buttonCaptionColor else ThemeElements.colors.primaryTextColor)
        )
    }
}

@Preview
@Composable
fun FeaturedProductGroupsPreview() {
    NiftyOrdersTheme {
        FeaturedProductGroups(
            featuredProductGroups = sampleProductGroups,
            selectedFeaturedProductGroupId = 1
        ) {
        }
    }
}

@Preview
@Composable
fun FeaturedProductGroupButtonPreview(
) {
    NiftyOrdersTheme {
        FeaturedProductGroupButton(
            caption = "Waffles",
            selected = true
        ) {
        }
    }
}