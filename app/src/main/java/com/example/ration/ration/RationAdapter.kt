package com.example.ration.ration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.databinding.ItemRationBinding
import com.example.ration.ration.models.DayRationModel

class RationAdapter(
    private val onRationItemListener: OnRationItemListener
) : RecyclerView.Adapter<RationViewHolder>() {

    private var dayRationList: List<DayRationModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RationViewHolder {
        val binding =
            ItemRationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RationViewHolder, position: Int) {
        holder.bind(dayRationList[position], onRationItemListener, position)
    }

    override fun getItemCount(): Int = dayRationList.size

    fun setData(list: List<DayRationModel>) {
        dayRationList = list
        notifyDataSetChanged()
    }
}