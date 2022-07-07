package com.pa.niftyorders.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pa.niftyorders.data.local.dao.ProductDAO
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product

@Database(entities = [Product::class, CartLine::class], version = 3)
@TypeConverters(Converters::class)
abstract class NiftyDataBase: RoomDatabase() {
    abstract val daoProduct: ProductDAO
    companion object{
        const val DATABASE_NAME = "nifty.db"
    }
}

