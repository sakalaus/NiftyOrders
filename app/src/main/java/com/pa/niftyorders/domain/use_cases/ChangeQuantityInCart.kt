package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.repository.Repository

class ChangeQuantityInCart(val repository: Repository) {
    suspend operator fun invoke(
        productsInCart: List<CartLine>,
        cartLineId: Long,
        changeQuantityBy: Int
    ) {
        val changeTotalPriceBy = productsInCart.first {
            it.id == cartLineId
        }.price * changeQuantityBy.toBigDecimal()
        repository.changeQuantityInCart(
            cartLineId = cartLineId,
            changeQuantityBy = changeQuantityBy,
            changeTotalPriceBy = changeTotalPriceBy
        )
    }
}