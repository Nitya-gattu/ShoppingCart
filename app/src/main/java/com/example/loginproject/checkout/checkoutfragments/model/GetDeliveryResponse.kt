package com.example.loginproject.checkout.checkoutfragments.model

import com.google.gson.annotations.SerializedName

data class GetDeliveryResponse(
    @SerializedName("status")
    val message: String,
    @SerializedName("message")
    val status: Int
)