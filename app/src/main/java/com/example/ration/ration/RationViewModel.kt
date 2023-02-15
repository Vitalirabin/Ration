package com.example.ration.ration

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ration.ProductModel
import com.example.ration.ration.models.DayRationForBDModel
import com.example.ration.ration.models.DayRationModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RationViewModel(private val useCase: RationUseCase) : ViewModel() {

    val rationList = MutableLiveData<MutableList<DayRationModel>>()
    val productList = MutableLiveData<List<ProductModel>>()
    val createNewRation = MutableLiveData<Boolean>()
    val calloriesOnDay = MutableLiveData<Int>()
    val activity = MutableLiveData<Int>()
    val purpose = MutableLiveData<Int>()
    val male = MutableLiveData<Boolean>()
    private var savedRation: List<DayRationForBDModel>? = listOf<DayRationForBDModel>()

    fun setProducts() {
        viewModelScope.launch {
            productList.value = useCase.getAllProducts()
        }
    }

    fun saveRation() {
        savedRation = rationList.value?.map {
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
        }?.toList()
        viewModelScope.launch {
            useCase.saveRation(savedRation ?: emptyList())
        }
    }

    fun setRationList() {
        viewModelScope.launch {
            val breakfasts = mutableListOf<ProductModel>()
            val seconds = mutableListOf<ProductModel>()
            val salads = mutableListOf<ProductModel>()
            val hotters = mutableListOf<ProductModel>()
            val drinks = mutableListOf<ProductModel>()
            useCase.getAllProducts().forEach {
                when (it.product_сategory) {
                    Constants.BREAKFAST -> {
                        breakfasts.add(it)
                    }
                    Constants.CATEGORY_SECOND -> {
                        seconds.add(it)
                    }
                    Constants.CATEGORY_HOTTER -> {
                        hotters.add(it)
                    }
                    Constants.CATEGORY_DRINKS -> {
                        drinks.add(it)
                    }
                    Constants.CATEGORY_SALADS -> {
                        salads.add(it)
                    }
                }
            }
            rationList.value = useCase.createRation(
                calloriesOnDay.value ?: 0,
                breakfasts,
                seconds,
                salads,
                hotters,
                drinks
            )
        }
    }

    fun setRationLastList() {
        viewModelScope.launch {
            if (useCase.getRation(calloriesOnDay.value ?: 0).isEmpty()) {
                setRationList()
            } else {
                rationList.value = useCase.getRation(
                    calloriesOnDay.value
                        ?: 0
                ).toMutableList()
            }
        }
    }

    fun changeRationElement(
        timeToEat: String,
        category: String,
        position: Int,
        name: String,
        calories: Int
    ) {
        viewModelScope.launch {
            rationList.value?.forEachIndexed { index, dayRationModel ->
                if (position == index) {
                    when (timeToEat) {
                        Constants.BREAKFAST -> {
                            when (category) {
                                Constants.BREAKFAST -> {
                                    val productModel = useCase.getProductByName(name)
                                    dayRationModel.breakfast.product = productModel
                                    dayRationModel.breakfast.product.weight =
                                        ((dayRationModel.breakfast.calories - dayRationModel.breakfast.drink.weight * dayRationModel.breakfast.drink.calories / 100) / (dayRationModel.breakfast.product.calories / 100)).toInt()
                                }
                                Constants.CATEGORY_DRINKS -> {
                                    val productModel = useCase.getProductByName(name)
                                    if ((calories / (productModel.calories / 100)).toInt() != 0)
                                        productModel.weight =
                                            (calories / (productModel.calories / 100)).toInt()
                                    else productModel.weight = 1
                                    productModel.weight = Constants.DRINKS_WEIGHT
                                    dayRationModel.breakfast.drink = productModel
                                    dayRationModel.breakfast.product.weight =
                                        ((dayRationModel.breakfast.calories - dayRationModel.breakfast.drink.weight * dayRationModel.breakfast.drink.calories / 100) / (dayRationModel.breakfast.product.calories / 100)).toInt()
                                }
                            }

                        }
                        Constants.LUNCH -> {
                            when (category) {
                                Constants.CATEGORY_SECOND -> {
                                    val productModel = useCase.getProductByName(name)
                                    productModel.weight =
                                        ((dayRationModel.lunch.calories - dayRationModel.lunch.salad.calories / 100 * dayRationModel.lunch.salad.weight - dayRationModel.lunch.drink.calories / 100 * dayRationModel.lunch.drink.weight) * 0.7).toInt()
                                    dayRationModel.lunch.second = productModel
                                }
                                Constants.CATEGORY_HOTTER -> {
                                    val productModel = useCase.getProductByName(name)
                                    productModel.weight =
                                        ((dayRationModel.lunch.calories - dayRationModel.lunch.salad.calories / 100 * dayRationModel.lunch.salad.weight - dayRationModel.lunch.drink.calories / 100 * dayRationModel.lunch.drink.weight) * 0.3).toInt()
                                    dayRationModel.lunch.hotter = productModel
                                }
                                Constants.CATEGORY_SALADS -> {
                                    val productModel = useCase.getProductByName(name)
                                    if ((calories / (productModel.calories / 100)).toInt() != 0)
                                        productModel.weight =
                                            (calories / (productModel.calories / 100)).toInt()
                                    else productModel.weight = 1
                                    dayRationModel.lunch.salad = productModel
                                }
                                Constants.CATEGORY_DRINKS -> {
                                    val productModel = useCase.getProductByName(name)
                                    if ((calories / (productModel.calories / 100)).toInt() != 0)
                                        productModel.weight =
                                            (calories / (productModel.calories / 100)).toInt()
                                    else productModel.weight = 1
                                    dayRationModel.lunch.drink = productModel
                                }
                            }
                        }
                        Constants.DINNER -> {
                            when (category) {
                                Constants.CATEGORY_SECOND -> {
                                    val productModel = useCase.getProductByName(name)
                                    dayRationModel.dinner.second = productModel
                                    dayRationModel.dinner.second.weight =
                                        (dayRationModel.dinner.calories - dayRationModel.dinner.salad.calories / 100 * dayRationModel.dinner.salad.weight - dayRationModel.dinner.drink.calories / 100 * dayRationModel.dinner.drink.weight).toInt()
                                }
                                Constants.CATEGORY_SALADS -> {
                                    val productModel = useCase.getProductByName(name)
                                    if ((calories / (productModel.calories / 100)).toInt() != 0)
                                        productModel.weight =
                                            (calories / (productModel.calories / 100)).toInt()
                                    else productModel.weight = 1
                                    dayRationModel.dinner.salad = productModel
                                }
                                Constants.CATEGORY_DRINKS -> {
                                    val productModel = useCase.getProductByName(name)
                                    if ((calories / (productModel.calories / 100)).toInt() != 0)
                                        productModel.weight =
                                            (calories / (productModel.calories / 100)).toInt()
                                    else productModel.weight = 1
                                    dayRationModel.dinner.drink = productModel
                                }
                            }
                        }
                    }
                }
            }
            val list = rationList.value
            rationList.value = list ?: mutableListOf()
        }
    }

    fun calculateCalories(context: Context, weight: Int, height: Int, age: Int) {
        if (createNewRation.value == true) {
            if (male.value == true) {
                calloriesOnDay.value =
                    (((66.47 + (13.75 * weight) + (5 * height) - (6.75 * age))
                            * (activity.value ?: 0) / 100
                            * (purpose.value ?: 0)) / 100
                            ).toInt()
            } else {
                calloriesOnDay.value =
                    (((665.09 + (9.56 * weight) + (1.84 * height) - (4.67 * age))
                            * (activity.value ?: 0) / 100
                            * (purpose.value ?: 0)) / 100
                            ).toInt()
            }
            context.getSharedPreferences(Constants.NAME_SP, Context.MODE_PRIVATE).edit()
                .putInt("lastCalories", calloriesOnDay.value ?: 0).apply()
            createNewRation.value = false
        } else {
            calloriesOnDay.value =
                context.getSharedPreferences(Constants.NAME_SP, Context.MODE_PRIVATE)
                    .getInt("lastCalories", 0)
            createNewRation.value = false
        }
    }
}