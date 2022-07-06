package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.repository.Repository
import com.pa.niftyorders.ui.screens.shopwindow.OrderLine

class GetProductsInCart(private val repository: Repository) {
    suspend operator fun invoke(): List<OrderLine>{
        return repository.getProductsInCart()
    }
}