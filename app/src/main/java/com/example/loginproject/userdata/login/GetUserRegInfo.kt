package com.example.loginproject.userdata.login

import com.google.gson.annotations.SerializedName

data class GetUserRegInfo(
    @SerializedName("user_id")
    val userId:Int,
    @SerializedName("full_name")
    val fullName:String,
    @SerializedName("mobile_no")
    val mobileNo:String,
    @SerializedName("email_id")
    val emailId:String,
)
