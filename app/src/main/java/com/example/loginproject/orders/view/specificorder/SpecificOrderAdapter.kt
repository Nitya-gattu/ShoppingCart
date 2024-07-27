package com.example.loginproject.orders.view.specificorder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.loginproject.databinding.SpecificOrderViewholderBinding
import com.example.loginproject.orders.model.specificorder.Item

class SpecificOrderAdapter(
    val itemsList: List<Item>
): Adapter<SpecificOrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificOrderViewHolder {
        val binding = SpecificOrderViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpecificOrderViewHolder(binding)
    }

    override fun getItemCount()= itemsList.size

    override fun onBindViewHolder(holder: SpecificOrderViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }
}