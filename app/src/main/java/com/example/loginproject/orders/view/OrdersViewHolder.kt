package com.example.loginproject.orders.view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.databinding.OrdersViewholderBinding
import com.example.loginproject.orders.model.Order

class OrdersViewHolder(
    val binding: OrdersViewholderBinding
): ViewHolder(binding.root) {

    fun bind(order: Order){
        with(binding){
            orderId.text = order.order_id
            orderDate.text = order.order_date
            orderStatus.text = order.order_status
            billAmount.text = order.bill_amount
            paymentMethod.text= order.payment_method
            addressTitle.text = order.address_title
            address.text = order.address
        }
    }

}