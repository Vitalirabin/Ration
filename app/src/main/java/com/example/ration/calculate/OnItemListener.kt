package com.example.ration.calculate

import com.example.ration.ProductModel

interface OnItemListener {
    fun onChangeWeight(position:Int, weight: Double)
    fun onClickDelete(productModel: ProductModel)
}
