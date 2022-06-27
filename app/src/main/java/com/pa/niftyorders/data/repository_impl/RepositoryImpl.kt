package com.pa.niftyorders.data.repository_impl

import com.pa.niftyorders.data.local.NiftyDataBase
import com.pa.niftyorders.data.local.entities.Product
import com.pa.niftyorders.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val db: NiftyDataBase
): Repository {
    val dao = db.daoProduct
    override suspend fun getTopProducts(): List<Product> {
        return dao.getAllProducts()
    }
}