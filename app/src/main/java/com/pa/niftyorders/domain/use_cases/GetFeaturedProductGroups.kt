package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.repository.Repository
import com.pa.niftyorders.utils.ALL_ITEMS_ID

class GetFeaturedProductGroups(private val repository: Repository) {
    suspend operator fun invoke(): List<ProductGroup> {
        val groupsFromRepository = repository.getFeaturedProductGroups()
        val allGroups = ProductGroup(
            id = ALL_ITEMS_ID,
            name = "All",
            description = "All products",
            imageUrl = "",
            featured = true
        )
        return mutableListOf(allGroups) + groupsFromRepository
    }
}