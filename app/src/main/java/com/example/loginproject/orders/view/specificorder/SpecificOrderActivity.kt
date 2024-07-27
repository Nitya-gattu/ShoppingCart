package com.example.loginproject.orders.view.specificorder

import android.os.Bundle
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginproject.R
import com.example.loginproject.databinding.ActivitySpecificOrderBinding
import com.example.loginproject.orders.model.specificorder.GetSpecificOrderResponse
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecificOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpecificOrderBinding
    val apiService =ApiClient.retrofit.create(ApiService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivitySpecificOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orderId = intent.getStringExtra("order_id") ?: 1
        loadItemList(orderId.toString())
    }

    private fun loadItemList(orderId: String?) {


        if (orderId != null) {
            apiService.getOrderItems(orderId.toInt()).enqueue(object :Callback<GetSpecificOrderResponse>{
                override fun onResponse(
                    call: Call<GetSpecificOrderResponse>,
                    response: Response<GetSpecificOrderResponse>
                ) {
                    if(!response.isSuccessful){
                        Toast.makeText(this@SpecificOrderActivity, "some error", Toast.LENGTH_LONG)
                    }

                    val items = response.body()
                    setupOrder(items)
                    if(items == null){
                        Toast.makeText(this@SpecificOrderActivity, "empty response from server", Toast.LENGTH_LONG)
                    }
                    binding.recyclerview.layoutManager = LinearLayoutManager(this@SpecificOrderActivity)
                    if (items != null) {
                        binding.recyclerview.adapter= SpecificOrderAdapter(items.order.items)
                    }
                }

                override fun onFailure(p0: Call<GetSpecificOrderResponse>, p1: Throwable) {
                    Toast.makeText(this@SpecificOrderActivity, "unknown error", Toast.LENGTH_LONG)
                }

            })
        }

    }

    private fun setupOrder(items: GetSpecificOrderResponse?) {
        with(binding){
            if (items != null) {
                orderId.text = items.order.order_id
                orderDate.text = items.order.order_date
                orderStatus.text = items.order.order_status
                billAmount.text = items.order.bill_amount
                paymentMethod.text = items.order.payment_method
                addressTitle.text = items.order.address_title
                address.text = items.order.address
            }
        }
    }
}