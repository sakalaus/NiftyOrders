package com.pa.niftyorders.ui.components.brandedtextfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme
import com.pa.niftyorders.ui.theme.ThemeElements

val STANDARD_FIELD_HEIGHT: Dp = 48.dp
val COLLAPSED_FIELD_WIDTH: Dp = 48.dp

@Composable
fun BrandedTextField(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean = false,
    hasError: Boolean = false,
    isCollapsed: Boolean = false,
    height: Dp = 0.dp,
    hint: String = "",
    color: Color = ThemeElements.colors.primaryBackgroundColor,
    showHint: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    textStyle: TextStyle = TextStyle(
        color = ThemeElements.colors.primaryTextColor,
        textAlign = TextAlign.Start
    ),
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {}
) {

    val boxModifier =
        modifier
            .height(if (height != 0.dp) height else STANDARD_FIELD_HEIGHT)
            .background(color).let {
                if (hasError) {
                    it.then(
                        Modifier
                            .border(width = 2.dp, color = ThemeElements.colors.errorColor)
                            .padding(4.dp)
                    )
                } else it
            }
            .then(if (isCollapsed) Modifier.width(COLLAPSED_FIELD_WIDTH) else Modifier.fillMaxWidth())

    val contentAlignment = if (isCollapsed) Alignment.Center else Alignment.CenterStart

    Box(
        modifier = boxModifier,
        contentAlignment = contentAlignment
    ) {
        BasicTextField(
            modifier = Modifier
                .matchParentSize()
                .padding(8.dp)
                .onFocusChanged {
                    onFocusChange(it)
                },
            value = text,
            keyboardOptions = keyboardOptions,
            readOnly = readOnly,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            cursorBrush = SolidColor(ThemeElements.colors.primaryTextColor)
        )
        if (showHint) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = hint,
                style = textStyle,
                color = ThemeElements.colors.secondaryTextColor,
            )
        }
    }
}

@Composable
@Preview
fun Preview() {
    NiftyOrdersTheme {
        BrandedTextField(
            modifier = Modifier
                .padding(8.dp),
            text = "Arriba, no abajo",
            hasError = true,
            isCollapsed = false,
            singleLine = true,
            textStyle = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = ThemeElements.colors.primaryTextColor,
                letterSpacing = 0.15.sp,
                textAlign = TextAlign.Left
            )
        )
    }
}