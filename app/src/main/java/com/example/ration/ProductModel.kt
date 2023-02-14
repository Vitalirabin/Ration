package com.example.ration

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductModel(
    @PrimaryKey
    val name: String,
    val calories: Double,
    val protein: Double,
    val fat: Double,
    val carbohydrate: Double,
    val product_сategory: String,
    var weight: Int
)
