package com.example.ration.calculate

import com.example.ration.ProductModel

interface OnItemListener {
    fun onChangeWeight(productModel: ProductModel, weight: Double)
    fun onClickDelete(productModel: ProductModel)
}
