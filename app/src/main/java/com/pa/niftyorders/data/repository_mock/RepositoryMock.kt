package com.pa.niftyorders.data.repository_mock

import com.pa.niftyorders.data.local.entities.Product
import com.pa.niftyorders.domain.repository.Repository

class RepositoryMock: Repository {
    override suspend fun getTopProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Cinnamon",
                description = "Straight out of Colombia",
                price = 200.99
            )
        )
    }
}