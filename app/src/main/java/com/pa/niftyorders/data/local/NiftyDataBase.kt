package com.pa.niftyorders.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pa.niftyorders.data.local.dao.ProductDAO
import com.pa.niftyorders.data.local.entities.Product

@Database(entities = [Product::class], version = 1)
abstract class NiftyDataBase: RoomDatabase() {
    abstract val daoProduct: ProductDAO
    companion object{
        const val DATABASE_NAME = "nifty.db"
    }
}