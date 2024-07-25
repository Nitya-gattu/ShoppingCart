package com.example.loginproject.productadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginproject.data.Category
import com.example.loginproject.databinding.ShoppingGridItemBinding

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