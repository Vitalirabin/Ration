package com.example.ration.ration.models

data class DayRationModel(
    val day: Int,
    val breakfast: BreakfastModel,
    val lunch: LunchModel,
    val dinner: DinnerModel,
)
