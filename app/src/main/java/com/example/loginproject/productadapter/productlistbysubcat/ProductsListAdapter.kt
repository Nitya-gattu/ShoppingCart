package com.example.loginproject.productadapter.productlistbysubcat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.loginproject.DatabaseHelper
import com.example.loginproject.data.subcategoryproducts.Product
import com.example.loginproject.databinding.ActivitySubcategoryitemBinding

class ProductsListAdapter(
    val product: List<Product>,
    val databaseHelper: DatabaseHelper
): Adapter<ProductsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListViewHolder {
        val binding = ActivitySubcategoryitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsListViewHolder(binding, databaseHelper)
    }

    override fun getItemCount() = product.size

    override fun onBindViewHolder(holder: ProductsListViewHolder, position: Int) {
        holder.bind(product[position], databaseHelper)
    }
}