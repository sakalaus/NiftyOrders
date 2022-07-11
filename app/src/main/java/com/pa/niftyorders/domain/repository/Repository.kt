package com.pa.niftyorders.domain.repository

import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product

interface Repository {
    suspend fun getTopProducts(): List<Product>
    suspend fun getProductsInCart(): List<CartLine>
    suspend fun changeQuantityInCart(cartLineId: Long, changeBy: Int): Unit
    suspend fun createDemoData(): Unit
}