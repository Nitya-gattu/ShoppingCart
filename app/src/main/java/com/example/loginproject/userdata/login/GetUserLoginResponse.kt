package com.example.loginproject.userdata.login

import com.google.gson.annotations.SerializedName

data class GetUserLoginResponse(
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("user")
    val user: GetUserRegInfo
)
