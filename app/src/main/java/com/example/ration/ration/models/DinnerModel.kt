package com.example.ration.ration.models

import com.example.ration.ProductModel

data class DinnerModel(
    var second: ProductModel,
    var salad: ProductModel,
    var drink: ProductModel,
    var calories: Int

)
