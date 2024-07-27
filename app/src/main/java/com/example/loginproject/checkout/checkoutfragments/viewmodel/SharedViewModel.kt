package com.example.loginproject.checkout.checkoutfragments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginproject.checkout.checkoutfragments.model.AddDeliveryAddress

class SharedViewModel:ViewModel() {
    private val _selectedPaymentType = MutableLiveData<String>()
    val selectedPaymentType :LiveData<String> = _selectedPaymentType

    private val _selectedAddress =MutableLiveData<List<String>>()
    val selectedAddress: LiveData<List<String>> = _selectedAddress


    fun selectedAddress(address:List<String>){
        _selectedAddress.postValue(address)
    }
    fun selectPaymentType(paymentType:String){
        _selectedPaymentType.postValue(paymentType)
    }
}