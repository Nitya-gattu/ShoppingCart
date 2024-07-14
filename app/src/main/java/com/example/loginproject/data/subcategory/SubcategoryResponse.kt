package com.example.loginproject.data.subcategory

import com.google.gson.annotations.SerializedName

data class SubcategoryResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("subcategories")
    val subcategories: List<Subcategory>
)