package com.pa.niftyorders.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class CartLine(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String = "",
    val productId: Long = -1,
    val quantity: Float = 0f,
    val price: BigDecimal = BigDecimal(0),
    val totalPrice: BigDecimal = BigDecimal(0),
    val imageUrl: String = ""
)