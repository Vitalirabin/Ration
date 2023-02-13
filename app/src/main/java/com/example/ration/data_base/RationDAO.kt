package com.example.ration.data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ration.ration.models.DayRationForBDModel

@Dao
interface RationDAO {
    @Query("SELECT * FROM `ration` WHERE day=:day")
    suspend fun getRationByDay(day: Int): DayRationForBDModel?

    @Query("SELECT * FROM `ration`")
    suspend fun getAllRation(): List<DayRationForBDModel>?

    @Insert
    suspend fun addRationData(PM: DayRationForBDModel)

    @Query("DELETE FROM 'ration' WHERE day = :day")
    suspend fun deleteRation(day: Int)
}