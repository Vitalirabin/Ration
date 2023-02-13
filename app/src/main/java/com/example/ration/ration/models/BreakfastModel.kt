package com.example.ration.ration.models

import com.example.ration.ProductModel

data class BreakfastModel(
    var product: ProductModel,
    var drink: ProductModel,
    var calories: Int
)
