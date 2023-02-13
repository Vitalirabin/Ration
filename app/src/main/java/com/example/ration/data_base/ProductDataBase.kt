package com.example.ration.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ration.ProductModel
import com.example.ration.ration.models.DayRationForBDModel
import com.example.ration.ration.models.DayRationModel

@Database(entities = [ProductModel::class], version = 1)
abstract class ProductDataBase : RoomDatabase() {
    abstract val productDAO: ProductDAO
}