package com.example.ration.calculate

import androidx.recyclerview.widget.DiffUtil
import com.example.ration.ProductModel


class ChooseDiffUtil : DiffUtil.ItemCallback<ProductModel>() {
    override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem.name == newItem.name
    }

}