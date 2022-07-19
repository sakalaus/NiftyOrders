package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.repository.Repository

class GetFeaturedProductGroups(private val repository: Repository) {
    suspend operator fun invoke(): List<ProductGroup>{
        return repository.getFeaturedProductGroups()
    }
}