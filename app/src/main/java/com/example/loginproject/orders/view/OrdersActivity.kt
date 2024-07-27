package com.example.loginproject.orders.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginproject.R
import com.example.loginproject.databinding.ActivityOrdersBinding
import com.example.loginproject.orders.viewmodel.OrdersViewModel

class OrdersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdersBinding
    val orderViewModel : OrdersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
    }

    private fun setupObservers() {
        orderViewModel.getOrders()
        orderViewModel.orderResponse.observe(this){
            binding.recyclerview.layoutManager = LinearLayoutManager(this)
            if (it != null) {
                binding.recyclerview.adapter = OrdersAdapter(it.orders)
            }
        }

        orderViewModel.error.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }
}