package com.example.loginproject.orders.model

data class GetOrdersResponse(
    val message: String,
    val orders: List<Order>,
    val status: Int
)