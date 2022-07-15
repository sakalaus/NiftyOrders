package com.pa.niftyorders.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class Promotion(
    @PrimaryKey
    val id: Long,
    val productId: Long,
    val name: String,
    val description: String,
    val discount: BigDecimal,
    val price: BigDecimal,
    val imageUrl: String,
) {
}