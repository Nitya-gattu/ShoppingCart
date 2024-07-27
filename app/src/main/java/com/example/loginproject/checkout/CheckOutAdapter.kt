package com.example.loginproject.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.DatabaseProduct
import com.example.loginproject.databinding.CheckoutItemViewholderBinding

class CheckOutAdapter(
    private var products: List<DatabaseProduct>
) : Adapter<CheckOutAdapter.CheckOutViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckOutViewHolder {
        val binding = CheckoutItemViewholderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CheckOutViewHolder(binding)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: CheckOutViewHolder, position: Int) {
        holder.bind(products[position])
    }


    inner class CheckOutViewHolder(
        val binding: CheckoutItemViewholderBinding
    ) : ViewHolder(binding.root) {

        fun bind(item: DatabaseProduct) {
            with(binding) {
                productName.text = item.productName
                unitPrice.text = "Unit Price:    $${item.productPrice}"
                quantity.text = "Quantity     ${item.productQuantity.toString()}"
                amount.text = "Amount      $${item.totalPrice}"
            }

        }
    }
}