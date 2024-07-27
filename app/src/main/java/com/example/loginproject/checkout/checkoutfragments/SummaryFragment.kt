package com.example.loginproject.checkout.checkoutfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.loginproject.DatabaseHelper
import com.example.loginproject.DatabaseProduct
import com.example.loginproject.R
import com.example.loginproject.checkout.CheckOutAdapter
import com.example.loginproject.checkout.checkoutfragments.viewmodel.SharedViewModel
import com.example.loginproject.databinding.FragmentCartItemsBinding
import com.example.loginproject.databinding.FragmentSummaryBinding


class SummaryFragment : Fragment() {
    lateinit var binding: FragmentSummaryBinding
    lateinit var databaseHelper: DatabaseHelper
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = DatabaseHelper(requireContext())

        val products = databaseHelper.readData()

        binding.recyclerView.adapter = CheckOutAdapter(products)
        updateTotalBillAmount(products)

        sharedViewModel.selectedPaymentType.observe(viewLifecycleOwner){
            paymentType->
            binding.paymentOption.text = "Payment Option : ${paymentType}"
        }

        sharedViewModel.selectedAddress.observe(viewLifecycleOwner){
             address->
            binding.addressTitle.text = address[0]
            binding.address.text =address[1]
        }

        binding.confirm.setOnClickListener {

        }

    }

    private fun updateTotalBillAmount(products: List<DatabaseProduct>) {
        val totalAMount = products.sumOf { it.totalPrice }
        binding.totalBill.text = "$ ${totalAMount}"
    }


}