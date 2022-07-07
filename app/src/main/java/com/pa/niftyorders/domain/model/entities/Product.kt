package com.pa.niftyorders.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class Product(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String
) {
}