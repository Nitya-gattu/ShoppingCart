package com.example.loginproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loginproject.databinding.ActivityHomeActivityBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeActivityBinding
    lateinit var shoppingitemAdapter:ShoppingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {
       val userEmail = SecuredSharedPrefference.getString(SecuredSharedPrefference.USER_EMAIL)
        binding.userEmail.text = "Welcome $userEmail"

        binding.logout.setOnClickListener{
            SecuredSharedPrefference.clearAllPreference()
            startActivity(Intent(this@HomeActivity, MainActivity::class.java))
            finish()
        }

        with(binding){
            recyclerView.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            shoppingitemAdapter = ShoppingItemAdapter(shoppingitems)
            recyclerView.adapter = shoppingitemAdapter
        }


    }

    val shoppingitems = mutableListOf(
        ShoppingItem(
            shoppingitemImage = R.drawable.smartphone,
            shoppinitemType = "Smart Phones"
        ),
        ShoppingItem(
            shoppingitemImage = R.drawable.laptop,
            shoppinitemType = "Laptops"
        ),
        ShoppingItem(
            shoppingitemImage = R.drawable.watch,
            shoppinitemType = "Watch"
        ),
        ShoppingItem(
            shoppingitemImage = R.drawable.womendress,
            shoppinitemType = "Women Dresses"
        ),
        ShoppingItem(
            shoppingitemImage = R.drawable.mendress,
            shoppinitemType = "Men Dresses"
        ),
        ShoppingItem(
            shoppingitemImage = R.drawable.jewelry,
            shoppinitemType = "Jewelry"
        )
    )
}