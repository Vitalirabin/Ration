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

    suspend fun saveRation(ration: List<DayRationModel>) {
        ration.forEach {
            repository.setRation(
                DayRationForBDModel(
                    it.day,
                    it.breakfast.product.name,
                    it.breakfast.drink.name,
                    it.lunch.hotter.name,
                    it.lunch.second.name,
                    it.lunch.salad.name,
                    it.lunch.drink.name,
                    it.dinner.second.name,
                    it.dinner.salad.name,
                    it.dinner.drink.name
                )
            )
        }
    }

    suspend fun getRation(caloriesOnDay: Int): List<DayRationModel> {
        val list = mutableListOf<DayRationModel>()
        repository.getRation().forEach { it ->
            val breakfast = BreakfastModel(
                getProductByName(it.breakfastProductName),
                getProductByName(it.breakfastDrinkName)
            )
            breakfast.drink.weight = Constants.DRINKS_WEIGHT
            breakfast.product.weight =
                (caloriesOnDay * Constants.BREAKFAST_PART / 100 - breakfast.drink.weight * breakfast.drink.calories / 100) / (breakfast.product.calories / 100)
            val lunch = LunchModel(
                getProductByName(it.lunchSecondName),
                getProductByName(it.lunchHotterkName),
                getProductByName(it.lunchSaladName),
                getProductByName(it.lunchDrinkName)
            )
            lunch.drink.weight = Constants.DRINKS_WEIGHT
            lunch.salad.weight = Constants.SALAD_WEIGHT
            lunch.hotter.weight =
                (caloriesOnDay * Constants.LUNCH_PART / 100 - lunch.salad.weight * lunch.salad.calories / 100 - lunch.drink.weight * lunch.drink.calories / 100) * 0.3 / (lunch.hotter.calories / 100)
            lunch.second.weight =
                (caloriesOnDay * Constants.LUNCH_PART / 100 - lunch.salad.weight * lunch.salad.calories / 100 - lunch.drink.weight * lunch.drink.calories / 100) * 0.7 / (lunch.second.calories / 100)
            val dinner = DinnerModel(
                getProductByName(it.dinerSecondName),
                getProductByName(it.dinerSaladName),
                getProductByName(it.dinerDrinkName)
            )
            dinner.drink.weight = Constants.DRINKS_WEIGHT
            dinner.salad.weight = Constants.SALAD_WEIGHT
            dinner.second.weight =
                (caloriesOnDay * Constants.DINNER_PART / 100 - dinner.salad.weight * dinner.salad.calories / 100 - dinner.drink.weight * dinner.drink.calories / 100) / (dinner.second.calories / 100)
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
                breakfasts[randList(breakfasts.size)],
                drinks[randList(drinks.size)]
            )
            breakfast.drink.weight = Constants.DRINKS_WEIGHT
            breakfast.product.weight =
                (caloriesOnDay * Constants.BREAKFAST_PART / 100 - breakfast.drink.weight * breakfast.drink.calories / 100) / (breakfast.product.calories / 100)
            val lunch = LunchModel(
                seconds[randList(seconds.size)],
                hotters[randList(hotters.size)],
                salads[randList(salads.size)],
                drinks[randList(drinks.size)]
            )
            lunch.drink.weight = Constants.DRINKS_WEIGHT
            lunch.salad.weight = Constants.SALAD_WEIGHT
            lunch.hotter.weight =
                (caloriesOnDay * Constants.LUNCH_PART / 100 - lunch.salad.weight * lunch.salad.calories / 100 - lunch.drink.weight * lunch.drink.calories / 100) * 0.3 / (lunch.hotter.calories / 100)
            lunch.second.weight =
                (caloriesOnDay * Constants.LUNCH_PART / 100 - lunch.salad.weight * lunch.salad.calories / 100 - lunch.drink.weight * lunch.drink.calories / 100) * 0.7 / (lunch.second.calories / 100)
            val dinner = DinnerModel(
                seconds[randList(seconds.size)],
                salads[randList(salads.size)],
                drinks[randList(drinks.size)]
            )
            dinner.drink.weight = Constants.DRINKS_WEIGHT
            dinner.salad.weight = Constants.SALAD_WEIGHT
            dinner.second.weight =
                (caloriesOnDay * Constants.DINNER_PART / 100 - dinner.salad.weight * dinner.salad.calories / 100 - dinner.drink.weight * dinner.drink.calories / 100) / (dinner.second.calories / 100)
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