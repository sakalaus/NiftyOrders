package com.pa.niftyorders.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
) {
}