package com.example.loginproject.productadapter.searchadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.loginproject.data.searchdata.Product
import com.example.loginproject.databinding.ActivitySubcategoryitemBinding

class SearchAdapter(
    val products: List<Product>
): Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ActivitySubcategoryitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount()= products.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
       holder.bind(products[position])
    }
}