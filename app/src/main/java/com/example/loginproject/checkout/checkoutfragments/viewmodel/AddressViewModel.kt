package com.example.loginproject.checkout.checkoutfragments.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginproject.checkout.checkoutfragments.model.AddDeliveryAddress
import com.example.loginproject.checkout.checkoutfragments.model.GetDeliveryResponse
import com.example.loginproject.checkout.checkoutfragments.model.local.AddressDatabase
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressViewModel(private val addressDb: AddressDatabase) : ViewModel(){
val apiService = ApiClient.retrofit.create(ApiService::class.java)
    val allAddress: LiveData<MutableList<AddDeliveryAddress>> = addressDb.addressDao.allAddress()

    private val _id= MutableLiveData<Int>()
    var id : LiveData<Int> = _id

    private val _error =MutableLiveData<String>()
    var error : LiveData<String> = _error

    private val _getDeliveryResult = MutableLiveData<GetDeliveryResponse?>()
    var getDeliveryResponse: MutableLiveData<GetDeliveryResponse?> = _getDeliveryResult

    fun addAddress(address: AddDeliveryAddress){
        val rowId = addressDb.addressDao.addAddress(address)
        if(rowId>0)  _id.postValue(rowId.toInt())
        else _error.postValue("failed to upload")

    }

    fun getAddressResponse(addAddress: AddDeliveryAddress){
        val call: Call<GetDeliveryResponse> = apiService.addAddress(addAddress)
        call.enqueue(object:Callback<GetDeliveryResponse>{
            override fun onResponse(
                call: Call<GetDeliveryResponse>,
                response: Response<GetDeliveryResponse>
            ) {
                if(!response.isSuccessful){
                    _error.postValue(response.errorBody().toString())
                }
                val response = response.body()
                if(response == null){
                    _error.postValue("empty response from server")
                }

                _getDeliveryResult.postValue(response)
            }

            override fun onFailure(p0: Call<GetDeliveryResponse>, p1: Throwable) {

            }

        })
    }



}

class ViewModelFactory(private val addressDb: AddressDatabase):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddressViewModel(addressDb) as T
    }
}