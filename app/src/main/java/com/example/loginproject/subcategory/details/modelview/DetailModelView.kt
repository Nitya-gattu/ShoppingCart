package com.example.loginproject.subcategory.details.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import com.example.loginproject.subcategory.details.data.GetDataResponse
import com.example.loginproject.subcategory.details.data.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailModelView : ViewModel(){
val apiservice = ApiClient.retrofit.create(ApiService::class.java)

    private val _productData = MutableLiveData<Product>()
    val productData: LiveData<Product> = _productData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>  = _error

    fun getDetails(productId:Int){
        val call = apiservice.getDetails(productId)
        call.enqueue(object: Callback<GetDataResponse> {
            override fun onResponse(call: Call<GetDataResponse>, response: Response<GetDataResponse>) {
                if(!response.isSuccessful){
                    _error.postValue(response.errorBody().toString())
                }

                val details = response.body()
                if(details == null){
                    _error.postValue("empty response from server")
                }

                if (details != null) {
                    _productData.postValue(details.product)
                }
            }

            override fun onFailure(p0: Call<GetDataResponse>, p1: Throwable) {
               _error.postValue(p1.printStackTrace().toString())
            }

        })
    }

}