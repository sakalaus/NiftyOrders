package com.pa.niftyorders.ui.components.brandedtextfield

data class TextFieldState(
  val text: String = "",
  val hint: String = "",
  val hintResId: Int = 0,
  val hasError: Boolean = false,
  val isHintVisible:Boolean = true
)