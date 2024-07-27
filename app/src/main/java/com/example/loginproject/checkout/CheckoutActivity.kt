package com.example.loginproject.checkout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.loginproject.R
import com.example.loginproject.checkout.checkoutfragments.CartItemsFragment
import com.example.loginproject.checkout.checkoutfragments.DeliveryFragment
import com.example.loginproject.checkout.checkoutfragments.PaymentFragment
import com.example.loginproject.checkout.checkoutfragments.SummaryFragment
import com.example.loginproject.checkout.checkoutfragments.viewmodel.SharedViewModel
import com.example.loginproject.databinding.ActivityCheckoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class CheckoutActivity : AppCompatActivity() {
    lateinit var binding:ActivityCheckoutBinding
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentList = listOf(CartItemsFragment(), DeliveryFragment(), PaymentFragment(), SummaryFragment())
        val adapter: CheckoutViewPagerAdapter = CheckoutViewPagerAdapter(this, fragmentList)
        binding.viewPager.adapter = adapter

        val tabText = listOf("Cart Items" , "Delivery", "Payment", "Summary")
        TabLayoutMediator(binding.tabs, binding.viewPager){
            tab, position->
            tab.text = tabText[position]
        }.attach()


        }
    fun navigateToNextPage(){
        if(binding.viewPager.currentItem < binding.viewPager.adapter!!.itemCount -1){
            binding.viewPager.currentItem += 1
        }
    }
}

