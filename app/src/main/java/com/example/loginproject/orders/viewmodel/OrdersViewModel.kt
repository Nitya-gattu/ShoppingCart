package com.example.loginproject.orders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginproject.orders.model.GetOrdersResponse
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersViewModel() : ViewModel(){
    private val _ordersResponse = MutableLiveData<GetOrdersResponse?>()
    val orderResponse: MutableLiveData<GetOrdersResponse?> = _ordersResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    val apiService = ApiClient.retrofit.create(ApiService::class.java)

    fun getOrders(){
        val call = apiService.getOrders(1)
        call.enqueue(object : Callback<GetOrdersResponse>{
            override fun onResponse(call: Call<GetOrdersResponse>, response: Response<GetOrdersResponse>) {
                if(!response.isSuccessful){
                    _error.postValue(response.errorBody().toString())
                }
                val responseBody = response.body()
                if(responseBody == null){
                    _error.postValue("empty response from server")
                }

                _ordersResponse.postValue(responseBody)
            }

            override fun onFailure(p0: Call<GetOrdersResponse>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}