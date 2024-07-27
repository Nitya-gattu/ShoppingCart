package com.example.loginproject.checkout.checkoutfragments.view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.checkout.checkoutfragments.model.AddDeliveryAddress
import com.example.loginproject.databinding.DeliveryaddressViewholderBinding

class AddressViewHolder(
    val binding: DeliveryaddressViewholderBinding,
    val onradioBtnClick: (Int)->Unit
) : ViewHolder(binding.root){

    init {
        binding.radioBtn.setOnClickListener{
            onradioBtnClick(adapterPosition)
        }
    }
    fun bind(address:AddDeliveryAddress, isSelected:Boolean){

        with(binding){
            title.text = address.title
            deladdress.text = address.address
            radioBtn.isChecked = isSelected

        }

    }
}