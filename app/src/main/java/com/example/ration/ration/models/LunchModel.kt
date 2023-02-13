package com.example.ration.ration.models

import com.example.ration.ProductModel

data class LunchModel(
    var second: ProductModel,
    var hotter: ProductModel,
    var salad: ProductModel,
    var drink: ProductModel,
    var calories: Int
)
