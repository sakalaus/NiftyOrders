package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Promotion
import com.pa.niftyorders.domain.repository.Repository

class GetPromotions(private val repository: Repository) {
    suspend operator fun invoke(): List<Promotion>{
        return repository.getPromotions()
    }
}