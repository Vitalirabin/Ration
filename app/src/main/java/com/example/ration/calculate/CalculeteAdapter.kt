package com.example.ration.calculate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.ProductModel
import com.example.ration.databinding.ItemCalculateProductBinding

class CalculeteAdapter(
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<CalculeteAdapter.MyViewHolder>() {

    var productList: List<ProductModel> = listOf()

    class MyViewHolder(private val binding: ItemCalculateProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            productModel: ProductModel, onItemListener: OnItemListener, position: Int
        ) {
            binding.itemCalculateTitleTextView.text = productModel.name
            if (productModel.weight != 0)
                binding.itemCalculateWeight.setText(productModel.weight.toString())
            else binding.itemCalculateWeight.setText("")
            binding.itemCalculateWeight.addTextChangedListener {
                if (binding.itemCalculateWeight.text.toString() != "") {
                    onItemListener.onChangeWeight(
                        productModel.name, it.toString().toInt()
                    )
                }
            }
            binding.itemCalculateDeleteButton.setOnClickListener {
                onItemListener.onClickDelete(productModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemCalculateProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(productList[position], onItemListener, position)
    }

    override fun getItemCount(): Int = productList.size

    fun setData(list: List<ProductModel>) {
        productList = list
        notifyDataSetChanged()
    }
}