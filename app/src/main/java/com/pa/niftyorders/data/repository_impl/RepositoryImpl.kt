package com.pa.niftyorders.data.repository_impl

import com.pa.niftyorders.data.local.NiftyDataBase
import com.pa.niftyorders.data.local.entities.Product
import com.pa.niftyorders.domain.repository.Repository
import com.pa.niftyorders.ui.screens.shopwindow.OrderLine
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: NiftyDataBase
): Repository {
    private val dao = db.daoProduct
    override suspend fun getTopProducts(): List<Product> {
        return dao.getAllProducts()
    }

    override suspend fun getProductsInCart(): List<OrderLine> {
        TODO("Not yet implemented")
    }
}