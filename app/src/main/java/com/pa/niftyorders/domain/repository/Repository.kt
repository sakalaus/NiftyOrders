package com.pa.niftyorders.domain.repository

import com.pa.niftyorders.data.local.entities.Product

interface Repository {
    suspend fun getTopProducts(): List<Product>
}