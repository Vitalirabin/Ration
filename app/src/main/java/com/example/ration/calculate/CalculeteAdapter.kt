package com.example.ration.calculate

import android.text.Editable
import android.text.TextWatcher
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
            productModel: ProductModel,
            onItemListener: OnItemListener,
        ) {
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString() != "" && binding.itemCalculateTitleTextView.text == productModel.name) {
                        onItemListener.onChangeWeight(
                            productModel.name, s.toString().toInt()
                        )
                    } else if (binding.itemCalculateTitleTextView.text == productModel.name)
                        onItemListener.onChangeWeight(
                            productModel.name, 0
                        )
                }
            }
            binding.itemCalculateTitleTextView.text = productModel.name
            binding.itemCalculateWeight.setText(productModel.weight.toString())
            if (binding.itemCalculateWeight.text.toString() == "0")
                binding.itemCalculateWeight.setText("")
            binding.itemCalculateWeight.addTextChangedListener(textWatcher)
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
        holder.bind(productList[position], onItemListener)
    }

    override fun getItemCount(): Int = productList.size

    fun setData(list: List<ProductModel>) {
        productList = list
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position
}