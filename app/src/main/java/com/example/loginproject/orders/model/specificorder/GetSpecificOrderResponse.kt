package com.example.loginproject.orders.model.specificorder

data class GetSpecificOrderResponse(
    val message: String,
    val order: Order,
    val status: Int
)