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

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * from Product WHERE groupId = :groupId " +
            "AND LOWER(name) LIKE '%' || :searchString || '%'")
    suspend fun getProductsInGroup(groupId: Long, searchString: String): List<Product>

    @Query("SELECT * FROM Product WHERE LOWER(name) LIKE '%' || :searchString || '%'")
    suspend fun getProducts(searchString: String): List<Product>
    
    @Query("SELECT * from ProductGroup")
    suspend fun getAllProductGroups(): List<ProductGroup>

    @Query("SELECT * from ProductGroup where featured")
    suspend fun getFeaturedProductGroups(): List<ProductGroup>

    @Query("SELECT * from Promotion")
    suspend fun getAllPromotions(): List<Promotion>

    @Query("SELECT * from CartLine")
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