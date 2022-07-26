package com.pa.niftyorders.ui.components.horizontalSectionHeader

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pa.niftyorders.ui.theme.ThemeElements

@Composable
fun HorizontalSectionHeader(
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