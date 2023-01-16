package com.example.ration.calculate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.ProductModel
import com.example.ration.databinding.ItemCalculateProductBinding

val diffCallback = ChooseDiffUtil()

class ChoseProductAdapter(
    val onItemListener: OnItemListener
) : ListAdapter<ProductModel, ChoseProductAdapter.MyViewHolder>(diffCallback) {


    class MyViewHolder(private val binding: ItemCalculateProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            productModel: ProductModel,
            onItemListener: OnItemListener
        ) {
            binding.itemCalculateTitleTextView.text = productModel.name
            binding.itemCalculateWeight.addTextChangedListener {
                var weight = 0.0
                if (binding.itemCalculateWeight.text.toString() != "") {
                    weight = binding.itemCalculateWeight.text.toString().toDouble()
                }
                onItemListener.onChangeWeight(
                    productModel,
                    weight
                )
            }
            binding.itemCalculateDeleteButton.setOnClickListener{
                onItemListener.onClickDelete(productModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCalculateProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category, onItemListener)
    }

}