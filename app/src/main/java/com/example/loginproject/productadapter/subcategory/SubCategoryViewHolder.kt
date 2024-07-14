package com.example.loginproject.productadapter.subcategory

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.R
import com.example.loginproject.SubCategoryProductListActivity
import com.example.loginproject.data.subcategory.Subcategory
import com.example.loginproject.databinding.ShoppingGridItemBinding
import com.squareup.picasso.Picasso
import okhttp3.internal.notifyAll

class SubCategoryViewHolder(
    val binding: ShoppingGridItemBinding
): ViewHolder(binding.root) {

    val base_imageUrl = "https://apolisrises.co.in/myshop/images/"
    fun bind(item: Subcategory){
        with(binding){
            val imageurl = base_imageUrl + item.subcategory_image_url
            Picasso.get().load(imageurl).error(R.drawable.defaultimage).into(categoryimg)
            tvCategoryname.text = item.subcategory_name


            root.setOnClickListener {

                val context = it.context
                val intent = Intent(context, SubCategoryProductListActivity::class.java)
                intent.putExtra("subCategoryId" , item.subcategory_id)
                context.startActivity(intent)
            }
        }


    }
}