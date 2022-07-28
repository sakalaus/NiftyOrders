package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.repository.Repository
import com.pa.niftyorders.utils.ALL_ITEMS_ID

class GetProductsInGroup(private val repository: Repository) {
    suspend operator fun invoke(groupId: Long, searchString: String): List<Product>{
        return if (groupId == ALL_ITEMS_ID){
            repository.getProducts(searchString)
        } else{
            repository.getProductsInGroup(groupId, searchString)
        }
    }
}