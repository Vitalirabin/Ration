package com.example.ration.data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ration.ProductModel
import com.example.ration.ration.models.DayRationForBDModel
import com.example.ration.ration.models.DayRationModel
import retrofit2.http.DELETE

@Dao
interface ProductDAO {

    @Query("SELECT * FROM `product` WHERE name=:name")
    suspend fun getByName(name: String): ProductModel?

    @Query("SELECT * FROM `product`")
    suspend fun getAllProducts(): List<ProductModel>?

    @Insert
    suspend fun addData(PM: ProductModel)

    @Query("DELETE FROM 'product' WHERE name = :name")
    suspend fun deleteProduct(name: String)
}