package com.pa.niftyorders.data.repository_mock

import com.pa.niftyorders.data.local.entities.Product
import com.pa.niftyorders.domain.repository.Repository
import javax.inject.Inject

class RepositoryMock @Inject constructor(): Repository {
    override suspend fun getTopProducts(): List<Product> {
        return sampleProducts
    }
}

val sampleProducts = listOf(
    Product(
        id = 1,
        name = "Adgeeka",
        description = "Straight out of Colombia",
        price = 200.99,
        imageUrl = "https://www.chefmarket.ru/blog/wp-content/uploads/2018/05/3_1522162464_3dc93.jpg"
    ),
    Product(
        id = 2,
        name = "Mojito",
        description = "Freshed out",
        price = 99.99,
        imageUrl = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/3279978A-FC6C-4231-A42C-DF759994C99C/Derivates/4278FB29-8E6B-4986-BF60-231C91231A01.jpg"
    ),
    Product(
        id = 3,
        name = "Paella",
        description = "De mariscos de la zona",
        price = 299.99,
        imageUrl = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/f3d7d1b4-4df6-4d72-b2f0-acf05cccae08/Derivates/4513fa48-f03c-4f8e-bea0-18385dd4bdd0.jpg"
    )

)