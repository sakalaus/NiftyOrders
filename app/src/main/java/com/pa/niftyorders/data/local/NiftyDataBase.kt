package com.pa.niftyorders.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pa.niftyorders.data.local.dao.ProductDAO
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.Promotion

@Database(entities = [Product::class, CartLine::class, Promotion::class], version = 5)
@TypeConverters(Converters::class)
abstract class NiftyDataBase: RoomDatabase() {
    abstract val daoProduct: ProductDAO
    companion object{
        const val DATABASE_NAME = "nifty.db"
    }
}

