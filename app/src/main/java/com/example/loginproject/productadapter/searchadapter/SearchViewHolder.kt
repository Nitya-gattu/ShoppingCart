package com.example.loginproject.productadapter.searchadapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.R
import com.example.loginproject.data.searchdata.Product
import com.example.loginproject.databinding.ActivitySubcategoryitemBinding
import com.squareup.picasso.Picasso

class SearchViewHolder(
    val binding: ActivitySubcategoryitemBinding
) : ViewHolder(binding.root){
    val baseimgurl = "https://apolisrises.co.in/myshop/images/"
    fun bind(product: Product){
        with(binding){
            productName.text = product.product_name
            productDiscription.text = product.description
            productunitPrice.text = product.price

            val imageurl = baseimgurl + product.product_image_url
                    Picasso.get().load(imageurl).error(R.drawable.defaultimage).into(productImage)
        }
    }
}