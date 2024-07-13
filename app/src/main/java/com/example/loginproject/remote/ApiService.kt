package com.example.loginproject.remote

import com.example.loginproject.userdata.GetUserRegResponse
import com.example.loginproject.userdata.UserRegInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-type: application/json")
    @POST("User/register")

    fun addUser(
        @Body addUserRegInfo: UserRegInfo
    ): Call<GetUserRegResponse>
}