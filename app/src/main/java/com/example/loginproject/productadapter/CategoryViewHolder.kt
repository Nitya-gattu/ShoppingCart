package com.example.loginproject.productadapter

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.loginproject.SubCategoryActivity
import com.example.loginproject.activity_total_billamount
import com.example.loginproject.data.Category
import com.example.loginproject.databinding.ShoppingGridItemBinding
import com.squareup.picasso.Picasso



 class CategoryViewHolder(private val itemBinding: ShoppingGridItemBinding):
    RecyclerView.ViewHolder(itemBinding.root){
     val imageBaseUrl = "https://apolisrises.co.in/myshop/images/"
    fun bind(item: Category){
        with(itemBinding){
            val imageUrl = imageBaseUrl + item.category_image_url
            Picasso.get().load(imageUrl).into(categoryimg)
            tvCategoryname.text = item.category_name


            root.setOnClickListener{
//                val context= it.context
//                val intent = Intent(context, activity_total_billamount::class.java)
//                context.startActivity(intent)

                val context = it.context
                val intent = Intent(context, SubCategoryActivity::class.java)
                intent.putExtra("categoryId" , item.category_id)
                context.startActivity(intent)
            }
        }
    }
}