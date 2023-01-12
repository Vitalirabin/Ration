package com.example.ration.chooseProduct

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.ProductModel
import com.example.ration.databinding.ItemChooseProductBinding

val diffCallback = ChooseDiffUtil()

class ChoseProductAdapter(
    val onClickListener: ItemOnClickListener
) : ListAdapter<ProductModel, ChoseProductAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(private val binding: ItemChooseProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            productModel: ProductModel,
            onClickListener: ItemOnClickListener
        ) {
            binding.nameTextView.text = productModel.name
            binding.caloriesTextView.text = productModel.calories.toString()
            binding.carbohydrateTextView.text = productModel.carbohydrate.toString()
            binding.proteinTextView.text = productModel.protein.toString()
            binding.fatTextView.text = productModel.fat.toString()
            binding.root.setOnClickListener { onClickListener.onClick(productModel.name) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemChooseProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category, onClickListener)
    }

}