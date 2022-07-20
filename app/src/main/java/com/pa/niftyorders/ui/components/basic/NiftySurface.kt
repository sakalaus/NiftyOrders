package com.pa.niftyorders.ui.components.basic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.pa.niftyorders.ui.theme.ThemeElements

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