package com.example.loginproject.userdata

import com.google.gson.annotations.SerializedName

data class UserRegInfo(
    @SerializedName("full_name")
    val fullName:String,
    @SerializedName("mobile_no")
    val mobileNo:String,
    @SerializedName("email_id")
    val emailId:String,
    @SerializedName("password")
    val password:String
)
