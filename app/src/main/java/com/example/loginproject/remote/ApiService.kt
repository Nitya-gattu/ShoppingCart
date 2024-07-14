package com.example.loginproject.remote

import com.example.loginproject.data.GetProductsCategory
import com.example.loginproject.data.subcategory.SubcategoryResponse
import com.example.loginproject.data.subcategoryproducts.GetProductsListResponse
import com.example.loginproject.userdata.GetUserRegResponse
import com.example.loginproject.userdata.UserRegInfo
import com.example.loginproject.userdata.login.GetUserLoginResponse
import com.example.loginproject.userdata.login.SendUserLoginInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-type: application/json")
    @POST("User/register")

    fun addUser(
        @Body addUserRegInfo: UserRegInfo
    ): Call<GetUserRegResponse>

    @Headers("Content-type: application/json")
    @POST("User/auth")
    fun getifLoginInfo(
        @Body loginInfo: SendUserLoginInfo
    ):Call<GetUserLoginResponse>

    @GET("Category")
    fun getProductCategory():Call<GetProductsCategory>

    @GET("SubCategory")
    fun getSubCategory(
        @Query("category_id") categoryId :String
    ):Call<SubcategoryResponse>

    @GET("SubCategory/products/{sub_category_id}")
    fun getProductsbySubCat(
        @Path ("sub_category_id") subcategoryId: String
    ):Call<GetProductsListResponse>
}