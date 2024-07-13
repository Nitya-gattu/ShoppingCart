package com.example.loginproject.data

import com.google.gson.annotations.SerializedName

data class GetProductsCategory(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("categories")
    val categories: List<Category>


)