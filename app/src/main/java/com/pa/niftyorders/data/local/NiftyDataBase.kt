package com.pa.niftyorders.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.pa.niftyorders.data.local.dao.ProductDAO
import com.pa.niftyorders.data.local.entities.Product
import java.math.BigDecimal

@Database(entities = [Product::class], version = 2)
abstract class NiftyDataBase: RoomDatabase() {
    abstract val daoProduct: ProductDAO
    companion object{
        const val DATABASE_NAME = "nifty.db"
    }
}

