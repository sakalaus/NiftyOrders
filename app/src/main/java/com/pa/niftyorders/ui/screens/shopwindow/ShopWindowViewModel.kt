package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pa.niftyorders.domain.use_cases.OrderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopWindowViewModel @Inject constructor(
    val orderUseCases: OrderUseCases
): ViewModel(){
    var uiState by mutableStateOf(ShopWindowState())
}