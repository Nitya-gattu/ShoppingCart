package com.example.loginproject.checkout.checkoutfragments.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginproject.checkout.checkoutfragments.model.AddDeliveryAddress

@Database(entities = [AddDeliveryAddress::class], version = 1)
abstract class AddressDatabase : RoomDatabase(){
    abstract val addressDao: AddressDao

    companion object{
        lateinit var addressDb : AddressDatabase
        fun getInstance(context: Context):AddressDatabase{
            if(!::addressDb.isInitialized){
                addressDb = Room.databaseBuilder(
                    context,
                    AddressDatabase::class.java,
                    "deliveryAdderess"
                ).allowMainThreadQueries().build()
            }
            return addressDb
        }
    }
}