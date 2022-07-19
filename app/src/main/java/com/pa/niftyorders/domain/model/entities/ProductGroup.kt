package com.pa.niftyorders.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class ProductGroup(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val featured: Boolean
) {
}