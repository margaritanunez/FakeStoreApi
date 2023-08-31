package com.example.fakestoreapi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_product_table")
data class DetailProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rate: Double,
    val count: Int
)
