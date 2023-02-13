package com.example.ration.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ration.ration.models.DayRationForBDModel


@Database(entities = [DayRationForBDModel::class], version = 1)
abstract class RationDataBase : RoomDatabase() {
    abstract val rationDAO: RationDAO
}