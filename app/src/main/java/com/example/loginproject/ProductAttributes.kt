package com.example.loginproject

import androidx.annotation.DrawableRes

data class ProductAttributes(
    val productName:String,
    val productId:Int,
    val productDescription:String,
    val productPrice:Int,
    val totalProductPrice:Int,
    val unit:String,
    val productQuantity:Int,
    @DrawableRes val productImage:Int
)

