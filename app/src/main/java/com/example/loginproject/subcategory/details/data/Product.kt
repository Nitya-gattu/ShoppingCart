package com.example.loginproject.subcategory.details.data

import com.google.gson.annotations.SerializedName

data class Product(
    val average_rating: String,
    val category_id: String,
    val description: String,
    val images: List<Image>,
    val is_active: String,
    val price: String,
    val product_id: String,
    val product_image_url: String,
    val product_name: String,
    @SerializedName("reviews")
    val reviews: List<Review>,
    val specifications: List<Specification>,
    val sub_category_id: String
)