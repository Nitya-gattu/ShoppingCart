package com.example.loginproject.checkout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CheckoutViewPagerAdapter (
    val fragmentActivity: FragmentActivity,
    val fragmentList : List<Fragment>
): FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}