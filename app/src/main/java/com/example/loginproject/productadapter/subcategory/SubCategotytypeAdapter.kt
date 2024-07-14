package com.example.loginproject.productadapter.subcategory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.loginproject.data.subcategory.Subcategory
import com.example.loginproject.databinding.ShoppingGridItemBinding

class SubCategotytypeAdapter(
    val item: List<Subcategory>
):Adapter<SubCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val binding = ShoppingGridItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SubCategoryViewHolder(binding)
    }

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        holder.bind(item[position])
    }
}