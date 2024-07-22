package com.example.loginproject.data.searchdata

data class GetSearchResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)