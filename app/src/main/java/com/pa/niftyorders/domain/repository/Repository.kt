package com.pa.niftyorders.domain.repository

import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.model.entities.Promotion
import java.math.BigDecimal

interface Repository {
    suspend fun getTopProducts(): List<Product>
    suspend fun getProductsInGroup(groupId: Long): List<Product>
    suspend fun getFeaturedProductGroups(): List<ProductGroup>
    suspend fun getProductsInCart(): List<CartLine>
    suspend fun changeQuantityInCart(cartLineId: Long, changeQuantityBy: Int, changeTotalPriceBy: BigDecimal): Unit
    suspend fun createDemoData(): Unit
    suspend fun addProductToCart(cartLine: CartLine)
    suspend fun getPromotions(): List<Promotion>
}