package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.repository.Repository

class AddProductToCart(val repository: Repository) {
    suspend operator fun invoke(cartLine: CartLine){
        repository.addProductToCart(cartLine)
    }
}