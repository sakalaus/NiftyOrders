package com.pa.niftyorders.data.repository_impl

import com.pa.niftyorders.data.local.NiftyDataBase
import com.pa.niftyorders.data.repository_mock.sampleCart
import com.pa.niftyorders.data.repository_mock.sampleProductGroups
import com.pa.niftyorders.data.repository_mock.sampleProducts
import com.pa.niftyorders.data.repository_mock.samplePromotions
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.model.entities.Promotion
import com.pa.niftyorders.domain.repository.Repository
import java.math.BigDecimal
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: NiftyDataBase
): Repository {

    private val dao = db.daoProduct

    override suspend fun getTopProducts(): List<Product> {
        return dao.getAllProducts()
    }

    override suspend fun getProductsInGroup(groupId: Long): List<Product> {
        return dao.getProductsInGroup(groupId)
    }

    override suspend fun getFeaturedProductGroups(): List<ProductGroup> {
        return dao.getFeaturedProductGroups()
    }

    override suspend fun getProductsInCart(): List<CartLine> {
        return dao.getCart()
    }

    override suspend fun changeQuantityInCart(cartLineId: Long, changeQuanityBy: Int, changeTotalPriceBy: BigDecimal) {
        return dao.changeQuantityInCart(cartLineId, changeQuanityBy, changeTotalPriceBy)
    }

    override suspend fun createDemoData() {
        dao.deleteAllProductGroups()
        dao.deleteAllProducts()
        dao.deleteWholeCart()
        dao.deleteAllPromotions()
        dao.createProductGroups(sampleProductGroups)
        dao.createProducts(sampleProducts)
        dao.createCart(sampleCart)
        dao.createPromotions(samplePromotions)
    }

    override suspend fun addProductToCart(cartline: CartLine) {
        dao.addProductToCart(cartline)
    }

    override suspend fun getPromotions(): List<Promotion> {
        return dao.getAllPromotions()
    }


}