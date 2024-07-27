package com.example.loginproject.checkout.checkoutfragments.view.paymentadapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.databinding.PaymentMethodViewholderBinding

class PaymentViewHolder(
    val binding: PaymentMethodViewholderBinding,
    val onRadioBtnClicked: (Int)->Unit
) : ViewHolder(binding.root){

    init {
        binding.radioBtn.setOnClickListener{
            onRadioBtnClicked(adapterPosition)
        }
    }
    fun bind(payType: String, isSelected:Boolean){
        binding.paymentType.text = payType
        binding.radioBtn.isChecked = isSelected
    }
}