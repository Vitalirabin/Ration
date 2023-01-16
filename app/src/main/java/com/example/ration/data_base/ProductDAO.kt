package com.example.ration.data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ration.ProductModel

@Dao
interface ProductDAO {

    @Query("SELECT * FROM `product` WHERE name=:name")
    suspend fun getByName(name: String): ProductModel?

    @Query("SELECT * FROM `product`")
    suspend fun getAllProducts(): List<ProductModel>?

    @Insert
    suspend fun addData(PM: ProductModel)
}