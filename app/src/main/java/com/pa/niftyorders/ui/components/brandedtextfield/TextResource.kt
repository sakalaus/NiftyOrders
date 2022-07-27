package com.pa.niftyorders.ui.components.brandedtextfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

class TextResource(
    private val resId: Int
) {
    @Composable
    fun getString(): String{
        return stringResource(id = resId)
    }
}