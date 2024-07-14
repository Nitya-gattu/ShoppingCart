package com.example.loginproject.data

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_id")
    val category_id: String = "1",
    @SerializedName("category_name")
    val category_name: String = "hello",
    @SerializedName("category_image_url")
    val category_image_url: String,

    @SerializedName("is_active")
    val is_active: String = "true"
)