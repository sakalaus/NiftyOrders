package com.pa.niftyorders.domain.repository

import com.pa.niftyorders.data.local.entities.Product
import com.pa.niftyorders.ui.screens.shopwindow.OrderLine

interface Repository {
    suspend fun getTopProducts(): List<Product>
    suspend fun getProductsInCart(): List<OrderLine>
}