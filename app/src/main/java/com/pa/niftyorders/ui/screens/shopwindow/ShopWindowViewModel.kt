package com.pa.niftyorders.ui.screens.shopwindow

import androidx.lifecycle.ViewModel
import com.pa.niftyorders.domain.use_cases.OrderUseCases
import javax.inject.Inject

class ShopWindowViewModel @Inject constructor(
    val orderUseCases: OrderUseCases
): ViewModel()