package com.example.loginproject.checkout.checkoutfragments.view.paymentadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.loginproject.databinding.PaymentMethodViewholderBinding

class PaymentAdapter(
    val paymentType: List<String>,
    val onPaymentTypeSelected: (String)->Unit
) : Adapter<PaymentViewHolder>(){
    private var selectedPosition: Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = PaymentMethodViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding){
                position ->
            val previousSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousSelectedPosition)
            notifyItemChanged(selectedPosition)
            onPaymentTypeSelected(paymentType[position])
        }
    }

    override fun getItemCount() = paymentType.size

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
       holder.bind(paymentType[position], position ==selectedPosition)
    }

}