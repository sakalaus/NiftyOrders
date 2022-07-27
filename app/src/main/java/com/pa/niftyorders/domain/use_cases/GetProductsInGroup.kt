package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.repository.Repository

class GetProductsInGroup(private val repository: Repository) {
    suspend operator fun invoke(groupId: Long, searchString: String): List<Product>{
        return repository.getProductsInGroup(groupId, searchString)
    }
}