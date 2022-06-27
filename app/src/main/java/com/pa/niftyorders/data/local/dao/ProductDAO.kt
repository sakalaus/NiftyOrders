package com.pa.niftyorders.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.pa.niftyorders.data.local.entities.Product

@Dao
interface ProductDAO {
    @Query("Select * from Product")
    suspend fun getAllProducts(): List<Product>
}