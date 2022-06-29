package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.data.local.entities.Product
import com.pa.niftyorders.domain.repository.Repository

class GetTopProducts(private val repository: Repository) {

    suspend operator fun invoke(): List<Product>{
        return repository.getTopProducts()
    }

}