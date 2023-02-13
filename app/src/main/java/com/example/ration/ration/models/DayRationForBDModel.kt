package com.example.ration.ration.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("ration")
data class DayRationForBDModel(
    @PrimaryKey
    val day: Int,
    val breakfastProductName:String,
    val breakfastDrinkName:String,
    val lunchHotterkName:String,
    val lunchSecondName:String,
    val lunchSaladName:String,
    val lunchDrinkName:String,
    val dinerSecondName:String,
    val dinerSaladName:String,
    val dinerDrinkName:String
)
