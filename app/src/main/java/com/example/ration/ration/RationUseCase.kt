package com.example.ration.ration

import android.util.Log
import com.example.ration.ProductModel
import com.example.ration.ration.models.*
import kotlinx.coroutines.delay

class RationUseCase(private val repository: RationRepository) {
    suspend fun getAllProducts(): List<ProductModel> {
        return repository.getAllProducts()
    }

    suspend fun getProductByName(name: String): ProductModel {
        return repository.getProductByName(name)
    }

    suspend fun saveRation(ration: List<DayRationForBDModel>) {
        ration.forEach {
            repository.setRation(it)
        }
    }

    suspend fun getRation(caloriesOnDay: Int): List<DayRationModel> {
        val list = mutableListOf<DayRationModel>()
        repository.getRation().forEach { it ->
            val breakfast = BreakfastModel(
                getProductByName(it.breakfastProductName),
                getProductByName(it.breakfastDrinkName), 0
            )
            delay(50)
            breakfast.drink.weight = Constants.DRINKS_WEIGHT
            breakfast.product.weight =
                ((caloriesOnDay * Constants.BREAKFAST_PART / 100 - breakfast.drink.weight * breakfast.drink.calories / 100) / (breakfast.product.calories / 100)).toInt()
            breakfast.calories = caloriesOnDay * Constants.BREAKFAST_PART / 100
            val lunch = LunchModel(
                getProductByName(it.lunchSecondName),
                getProductByName(it.lunchHotterkName),
                getProductByName(it.lunchSaladName),
                getProductByName(it.lunchDrinkName),
                0
            )
            delay(50)
            lunch.calories = caloriesOnDay * Constants.LUNCH_PART / 100
            lunch.drink.weight = Constants.DRINKS_WEIGHT
            lunch.salad.weight = Constants.SALAD_WEIGHT
            lunch.hotter.weight =
                ((caloriesOnDay * Constants.LUNCH_PART / 100 - lunch.salad.weight * lunch.salad.calories / 100 - lunch.drink.weight * lunch.drink.calories / 100) * 0.3 / (lunch.hotter.calories / 100)).toInt()
            lunch.second.weight =
                ((caloriesOnDay * Constants.LUNCH_PART / 100 - lunch.salad.weight * lunch.salad.calories / 100 - lunch.drink.weight * lunch.drink.calories / 100) * 0.7 / (lunch.second.calories / 100)).toInt()
            val dinner = DinnerModel(
                getProductByName(it.dinerSecondName),
                getProductByName(it.dinerSaladName),
                getProductByName(it.dinerDrinkName), 0
            )
            delay(50)
            dinner.calories = caloriesOnDay * Constants.DINNER_PART / 100
            dinner.drink.weight = Constants.DRINKS_WEIGHT
            dinner.salad.weight = Constants.SALAD_WEIGHT
            dinner.second.weight =
                ((caloriesOnDay * Constants.DINNER_PART / 100 - dinner.salad.weight * dinner.salad.calories / 100 - dinner.drink.weight * dinner.drink.calories / 100) / (dinner.second.calories / 100)).toInt()
            list.add(
                DayRationModel(it.day, breakfast, lunch, dinner)
            )
        }
        list.forEach {
            Log.d("List", it.day.toString())
        }
        return list
    }

    fun createRation(
        caloriesOnDay: Int,
        breakfasts: MutableList<ProductModel>,
        seconds: MutableList<ProductModel>,
        salads: MutableList<ProductModel>,
        hotters: MutableList<ProductModel>,
        drinks: MutableList<ProductModel>
    ): MutableList<DayRationModel> {
        val ration = mutableListOf<DayRationModel>()
        for (i in 1..7) {
            val breakfast = BreakfastModel(
                if (breakfasts.size != 0) breakfasts[randList(breakfasts.size)]
                else ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0),
                if (drinks.size != 0) drinks[randList(drinks.size)]
                else ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0), 0
            )
            breakfast.calories = caloriesOnDay * Constants.BREAKFAST_PART / 100
            breakfast.drink.weight = Constants.DRINKS_WEIGHT
            breakfast.product.weight =
                ((caloriesOnDay * Constants.BREAKFAST_PART / 100 - breakfast.drink.weight * breakfast.drink.calories / 100) / (breakfast.product.calories / 100)).toInt()
            val lunch = LunchModel(
                if (seconds.size != 0) seconds[randList(seconds.size)]
                else ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0),
                if (hotters.size != 0) hotters[randList(hotters.size)]
                else ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0),
                if (salads.size != 0) salads[randList(salads.size)]
                else ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0),
                if (drinks.size != 0) drinks[randList(drinks.size)]
                else ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0), 0
            )
            lunch.calories = caloriesOnDay * Constants.LUNCH_PART / 100
            lunch.drink.weight = Constants.DRINKS_WEIGHT
            lunch.salad.weight = Constants.SALAD_WEIGHT
            lunch.hotter.weight =
                ((caloriesOnDay * Constants.LUNCH_PART / 100 - lunch.salad.weight * lunch.salad.calories / 100 - lunch.drink.weight * lunch.drink.calories / 100) * 0.3 / (lunch.hotter.calories / 100)).toInt()
            lunch.second.weight =
                ((caloriesOnDay * Constants.LUNCH_PART / 100 - lunch.salad.weight * lunch.salad.calories / 100 - lunch.drink.weight * lunch.drink.calories / 100) * 0.7 / (lunch.second.calories / 100)).toInt()
            val dinner = DinnerModel(
                if (seconds.size != 0) seconds[randList(seconds.size)]
                else ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0),
                if (salads.size != 0) salads[randList(salads.size)]
                else ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0),
                if (drinks.size != 0) drinks[randList(drinks.size)]
                else ProductModel("", 0.0, 0.0, 0.0, 0.0, "", 0), 0
            )
            dinner.calories = caloriesOnDay * Constants.DINNER_PART / 100
            dinner.drink.weight = Constants.DRINKS_WEIGHT
            dinner.salad.weight = Constants.SALAD_WEIGHT
            dinner.second.weight =
                ((caloriesOnDay * Constants.DINNER_PART / 100 - dinner.salad.weight * dinner.salad.calories / 100 - dinner.drink.weight * dinner.drink.calories / 100) / (dinner.second.calories / 100)).toInt()
            ration.add(
                DayRationModel(
                    i,
                    breakfast,
                    lunch, dinner
                )
            )
        }
        return ration
    }

    private fun randList(end: Int): Int {
        require(0 <= end - 1) { "Illegal Argument" }
        return (0 until end).random()
    }

    private fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }
}