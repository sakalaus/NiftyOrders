package com.pa.niftyorders.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class CartLine(
    @PrimaryKey
    val id: Long,
    val name: String,
    val productId: Long,
    val quantity: Float,
    val price: BigDecimal,
    val totalPrice: BigDecimal,
    val imageUrl: String
)