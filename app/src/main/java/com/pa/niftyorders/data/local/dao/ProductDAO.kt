package com.pa.niftyorders.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import java.math.BigDecimal

@Dao
interface ProductDAO {

    @Query("Select * from Product")
    suspend fun getAllProducts(): List<Product>

    @Query("Select * from CartLine")
    suspend fun getCart(): List<CartLine>

    @Query("Delete from Product")
    suspend fun deleteAllProducts(): Unit

    @Query("Delete from CartLine")
    suspend fun deleteWholeCart(): Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCart(products: CartLine)

    @Query("Update cartLine SET quantity = quantity + :changeBy, totalPrice = totalPrice + :changeTotalPriceBy WHERE id == :cartLineId")
    suspend fun changeQuantityInCart(cartLineId: Long, changeBy: Int, changeTotalPriceBy: BigDecimal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createDemoProducts(products: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createDemoCart(products: List<CartLine>)

}