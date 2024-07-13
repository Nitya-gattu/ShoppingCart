package com.example.loginproject.productadapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginproject.activity_total_billamount
import com.example.loginproject.data.Category
import com.example.loginproject.databinding.ShoppingGridItemBinding
import com.squareup.picasso.Picasso

class ShoppingItemAdapter(
    private val itemlist: List<Category>
):RecyclerView.Adapter<CategoryViewHolder>() {

    private lateinit var itemBinding:ShoppingGridItemBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        itemBinding = ShoppingGridItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CategoryViewHolder(itemBinding)
    }

    override fun getItemCount() = itemlist.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(itemlist[position])
    }



}