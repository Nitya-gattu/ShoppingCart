package com.example.loginproject.checkout.checkoutfragments.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.loginproject.checkout.checkoutfragments.model.AddDeliveryAddress
import com.example.loginproject.databinding.DeliveryaddressViewholderBinding

class AddressAdapter(
    val addressList: List<AddDeliveryAddress>,
    val onselectedAddress: (AddDeliveryAddress)->Unit
): Adapter<AddressViewHolder>() {
    var selectedPosition:Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding= DeliveryaddressViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressViewHolder(binding){
            position->
            val previousSelectedPos = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousSelectedPos)
            notifyItemChanged(selectedPosition)
            onselectedAddress(addressList[position])
        }
    }

    override fun getItemCount() = addressList.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(addressList[position], position == selectedPosition)
    }
}