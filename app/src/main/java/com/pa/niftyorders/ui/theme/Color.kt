package com.pa.niftyorders.ui.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val primaryBackgroundColor: Color,
    val secondaryBackgroundColor: Color,
    val headerTextColor: Color,
    val primaryTextColor: Color,
    val primaryTextInvertColor: Color,
    val primaryTintColor: Color,
    val secondaryTintColor: Color,
    val hintColor: Color,
    val accentColor: Color,
    val notificationColor: Color
)

val lightPalette = Colors(
    primaryBackgroundColor = Color.White,
    secondaryBackgroundColor = Color(0xFFD0CCC7),
    headerTextColor = Color(0xFF311F09),
    primaryTextColor = Color(0xFF311F09),
    primaryTextInvertColor = Color.White,
    primaryTintColor = Color(0xFFFF8A00),
    secondaryTintColor = Color(0xFF3FA72F),
    hintColor = Color(0xFFA0978C),
    accentColor = Color(0xFF8AEAFF),
    notificationColor = Color.Red
)