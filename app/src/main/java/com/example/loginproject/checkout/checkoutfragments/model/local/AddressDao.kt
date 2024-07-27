package com.example.loginproject.checkout.checkoutfragments.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.loginproject.checkout.checkoutfragments.model.AddDeliveryAddress
@Dao
interface AddressDao {

    @Insert
    fun addAddress(address:AddDeliveryAddress):Long

    @Query("SELECT * FROM address")
    fun allAddress(): LiveData<MutableList<AddDeliveryAddress>>
}