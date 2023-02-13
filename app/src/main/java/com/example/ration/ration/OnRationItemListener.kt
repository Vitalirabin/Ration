package com.example.ration.ration

interface OnRationItemListener {
    fun onChangeProduct(timeToEat: String, category: String, position: Int, calories: Int)
}