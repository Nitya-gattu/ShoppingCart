package com.example.loginproject.checkout.checkoutfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loginproject.DatabaseHelper
import com.example.loginproject.DatabaseProduct
import com.example.loginproject.R
import com.example.loginproject.checkout.CheckOutAdapter
import com.example.loginproject.checkout.CheckoutActivity
import com.example.loginproject.databinding.FragmentCartItemsBinding


class CartItemsFragment : Fragment() {

lateinit var binding: FragmentCartItemsBinding
lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseHelper = DatabaseHelper(requireContext())
        val products = databaseHelper.readData()

        binding.recyclerView.adapter = CheckOutAdapter(products)
        updateTotalBillAmount(products)

        binding.next.setOnClickListener {
            (activity as CheckoutActivity).navigateToNextPage()
        }

    }

    private fun updateTotalBillAmount(products: List<DatabaseProduct>) {
        val totalAMount = products.sumOf { it.totalPrice }
        binding.totalBill.text = "$ ${totalAMount}"
    }


}