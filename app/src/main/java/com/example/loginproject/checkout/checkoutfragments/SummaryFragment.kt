package com.example.loginproject.checkout.checkoutfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.loginproject.DatabaseHelper
import com.example.loginproject.DatabaseProduct
import com.example.loginproject.R
import com.example.loginproject.checkout.CheckOutAdapter
import com.example.loginproject.checkout.checkoutfragments.viewmodel.SharedViewModel
import com.example.loginproject.data.orderdata.DeliveryAddress
import com.example.loginproject.data.orderdata.GetOrderResponse
import com.example.loginproject.data.orderdata.Item
import com.example.loginproject.data.orderdata.PostOrder
import com.example.loginproject.databinding.FragmentCartItemsBinding
import com.example.loginproject.databinding.FragmentSummaryBinding
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SummaryFragment : Fragment() {
    lateinit var binding: FragmentSummaryBinding
    lateinit var databaseHelper: DatabaseHelper
    val apiService: ApiService = ApiClient.retrofit.create(ApiService::class.java)
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
                placeOrder(products)
        }

    }

    private fun placeOrder(products: List<DatabaseProduct>) {
        val userId = 1
        val address = sharedViewModel.selectedAddress.value?.let {
            DeliveryAddress(it[0], it[1])
        } ?: return

        val paymentMethod = sharedViewModel.selectedPaymentType.value ?: return
        val items = products.map {
            Item(
                product_id = it.productId,
                quantity = it.productQuantity.toString(),
                unit_price = it.productPrice.toString()
            )
        }

        val billAmount = products.sumOf { it.totalPrice }

        val order = PostOrder(
            user_id = userId,
            bill_amount = billAmount,
            delivery_address = address,
            items = items,
            payment_method = paymentMethod
        )

        val call = apiService.placeOrder(order)
        call.enqueue(object: Callback<GetOrderResponse>{
            override fun onResponse(call: Call<GetOrderResponse>, response: Response<GetOrderResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Order placed successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to place order", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<GetOrderResponse>, p1: Throwable) {
                p1.printStackTrace()
                Toast.makeText(requireContext(), "unknown error: ${p1.printStackTrace()}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun updateTotalBillAmount(products: List<DatabaseProduct>) {
        val totalAMount = products.sumOf { it.totalPrice }
        binding.totalBill.text = "$ ${totalAMount}"
    }


}