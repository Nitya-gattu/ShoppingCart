package com.example.loginproject.checkout.checkoutfragments.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "address")
data class AddDeliveryAddress (
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("address")
    val address: String

    )