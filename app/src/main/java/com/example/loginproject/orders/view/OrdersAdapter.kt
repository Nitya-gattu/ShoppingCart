package com.example.loginproject.orders.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.loginproject.databinding.OrdersViewholderBinding
import com.example.loginproject.orders.model.Order

class OrdersAdapter(
    private val ordersList: List<Order>
) : Adapter<OrdersViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = OrdersViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding)
    }

    override fun getItemCount() = ordersList.size

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(order = ordersList[position])
    }
}