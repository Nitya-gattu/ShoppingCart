package com.example.loginproject.checkout.checkoutfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.loginproject.R
import com.example.loginproject.checkout.CheckoutActivity
import com.example.loginproject.checkout.checkoutfragments.view.paymentadapter.PaymentAdapter
import com.example.loginproject.checkout.checkoutfragments.viewmodel.SharedViewModel
import com.example.loginproject.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment() {
  private lateinit var binding: FragmentPaymentBinding
  private val sharedViewModel:SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val paymentList = listOf("Cash On Delivery" , "Internet Banking", " Debit Card / Credit Card", "Pay Pal")
        val adapter = PaymentAdapter(paymentList){
            paymentType->
            sharedViewModel.selectPaymentType(paymentType)
        }
        binding.recyclerView.adapter = adapter

        binding.next.setOnClickListener {
            (activity as CheckoutActivity).navigateToNextPage()
        }

    }

}