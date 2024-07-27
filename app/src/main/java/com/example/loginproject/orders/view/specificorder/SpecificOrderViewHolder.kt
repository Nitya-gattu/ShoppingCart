package com.example.loginproject.orders.view.specificorder

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.R
import com.example.loginproject.SubCategoryActivity
import com.example.loginproject.databinding.SpecificOrderViewholderBinding
import com.example.loginproject.orders.model.specificorder.Item
import com.squareup.picasso.Picasso

class SpecificOrderViewHolder(
    val binding: SpecificOrderViewholderBinding
): ViewHolder(binding.root) {
    val baseimgurl = "https://apolisrises.co.in/myshop/images/"
    fun bind(item: Item){
        with(binding){
            productName.text = item.product_name
            unitPrice.text= item.unit_price
            quantity.text = item.quantity
            amount.text = item.amount
            val imageurl = baseimgurl + item.product_image_url
            Picasso.get().load(imageurl).error(R.drawable.defaultimage).into(productImage)


        }
    }
}