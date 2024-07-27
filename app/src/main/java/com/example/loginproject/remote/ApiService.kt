package com.example.loginproject.remote

import com.example.loginproject.checkout.checkoutfragments.model.AddDeliveryAddress
import com.example.loginproject.checkout.checkoutfragments.model.GetDeliveryResponse
import com.example.loginproject.data.GetProductsCategory
import com.example.loginproject.data.orderdata.GetOrderResponse
import com.example.loginproject.data.orderdata.PostOrder
import com.example.loginproject.data.searchdata.GetSearchResponse
import com.example.loginproject.data.subcategory.SubcategoryResponse
import com.example.loginproject.data.subcategoryproducts.GetProductsListResponse
import com.example.loginproject.orders.model.GetOrdersResponse
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

    @GET("Product/search")
    fun searchProducts(
        @Query("query") searchtxt: String
    ): Call<GetSearchResponse>

    @Headers("Content-type: application/json")
    @POST("User/address")
    fun addAddress(
        @Body deliveryAddress: AddDeliveryAddress
    ): Call<GetDeliveryResponse>

    @Headers("Content-type: application/json")
    @POST("Order")
    fun placeOrder(
        @Body order: PostOrder
    ):Call<GetOrderResponse>

    @GET("Order/userOrders/{user_id}")
    fun getOrders(
        @Path("user_id") orderId:Int
    ): Call<GetOrdersResponse>

}