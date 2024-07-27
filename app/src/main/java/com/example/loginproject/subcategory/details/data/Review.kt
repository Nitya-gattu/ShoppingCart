package com.example.loginproject.subcategory.details.data

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("user_id")
    val user_id : String,

    @SerializedName("full_name")
    val full_name : String,
    @SerializedName("review_id")
    val review_id : String,
    @SerializedName("review_title")
    val review_title : String,
    @SerializedName("review")
    val review : String,
    @SerializedName("rating")
    val rating : String,
    @SerializedName("review_date")
    val review_date : String,


)
