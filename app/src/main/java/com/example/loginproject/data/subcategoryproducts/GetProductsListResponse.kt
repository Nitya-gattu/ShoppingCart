package com.example.loginproject.data.subcategoryproducts

import com.google.gson.annotations.SerializedName

data class GetProductsListResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("products")
    val products: List<Product>

)