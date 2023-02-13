package com.example.ration.calculate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import java.util.*


class ChoseProductAdapter(private val onClickListener:OnDialogItemClick) :
    RecyclerView.Adapter<ChoseProductAdapter.DataViewHolder>(), Filterable {

    var productList: ArrayList<DialogProductModel> = ArrayList()
    var productListFiltered: ArrayList<DialogProductModel> = ArrayList()

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: DialogProductModel,onDialogItemClick: OnDialogItemClick) {
            itemView.findViewById<TextView>(R.id.title_text_view).text = result.title
            itemView.findViewById<TextView>(R.id.subtitle_text_view).text = result.subTitle
            itemView.setOnClickListener {
                onDialogItemClick.onClick(result.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_choose_product, parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(productListFiltered[position],onClickListener)
    }

    override fun getItemCount(): Int = productListFiltered.size

    fun setData(list: List<DialogProductModel>) {
        productList = list as ArrayList<DialogProductModel>
        productListFiltered = productList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) productListFiltered = productList else {
                    val filteredList = ArrayList<DialogProductModel>()
                    productList
                        .filter {
                            (it.title.toLowerCase(Locale.ROOT).contains(constraint.toString().toLowerCase(Locale.ROOT)))
                        }
                        .forEach { filteredList.add(it) }
                    productListFiltered = filteredList

                }
                return FilterResults().apply { values = productListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                productListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<DialogProductModel>
                notifyDataSetChanged()
            }
        }
    }
}