package com.example.loginproject.userdata

import com.google.gson.annotations.SerializedName

data class GetUserRegResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message:String
)
