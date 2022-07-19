package com.pa.niftyorders.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.model.entities.Promotion
import java.math.BigDecimal

@Dao
interface ProductDAO {

    @Query("Select * from Product")
    suspend fun getAllProducts(): List<Product>

    @Query("Select * from Product where groupId = :groupId")
    suspend fun getProductsInGroup(groupId: Long): List<Product>

    @Query("Select * from ProductGroup")
    suspend fun getAllProductGroups(): List<ProductGroup>

    @Query("Select * from ProductGroup where featured")
    suspend fun getFeaturedProductGroups(): List<ProductGroup>

    @Query("Select * from Promotion")
    suspend fun getAllPromotions(): List<Promotion>

    @Query("Select * from CartLine")
    suspend fun getCart(): List<CartLine>

    @Query("Delete from Product")
    suspend fun deleteAllProducts(): Unit

    @Query("Delete from ProductGroup")
    suspend fun deleteAllProductGroups(): Unit

    @Query("Delete from Promotion")
    suspend fun deleteAllPromotions(): Unit

    @Query("Delete from CartLine")
    suspend fun deleteWholeCart(): Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCart(products: CartLine)

    @Query("Update cartLine SET quantity = quantity + :changeBy, totalPrice = totalPrice + :changeTotalPriceBy WHERE id == :cartLineId")
    suspend fun changeQuantityInCart(cartLineId: Long, changeBy: Int, changeTotalPriceBy: BigDecimal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProducts(products: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProductGroups(products: List<ProductGroup>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPromotions(products: List<Promotion>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCart(products: List<CartLine>)

}