package com.example.loginproject.userdata.login

import com.google.gson.annotations.SerializedName

data class SendUserLoginInfo(
    @SerializedName("email_id")
    val emailId:String,

    @SerializedName("password")
    val password:String
)
