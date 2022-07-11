package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.repository.Repository

class ChangeQuantityInCart(val repository: Repository) {
    suspend operator fun invoke(cartLineId: Long, changeBy: Int) {
        repository.changeQuantityInCart(cartLineId = cartLineId, changeBy = changeBy)
    }
}