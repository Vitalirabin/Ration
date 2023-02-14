package com.example.ration.calculate

import com.example.ration.ProductModel

interface OnItemListener {
    fun onChangeWeight(name:String, weight: Int)
    fun onClickDelete(productModel: ProductModel)
}
