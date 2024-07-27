package com.example.loginproject.subcategory.details

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginproject.DatabaseHelper
import com.example.loginproject.DatabaseProduct
import com.example.loginproject.R
import com.example.loginproject.databinding.ActivityDetailsBinding
import com.example.loginproject.databinding.ReviewViewholderBinding
import com.example.loginproject.subcategory.details.modelview.DetailModelView
import com.example.loginproject.subcategory.details.view.ReviewAdapter
import com.example.loginproject.subcategory.details.view.SpecificationAdapter
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    private val detailModelView : DetailModelView by viewModels()
    val databaseHelper = DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getStringExtra("productId") ?: return
        loadDetails(productId.toInt())
        binding.addtoCart.setOnClickListener{
          addToCart()

        }


    }

    private fun addToCart() {
        detailModelView.productData.observe(this@DetailsActivity){
            val cartItem = DatabaseProduct(
                productId = it.product_id.toInt(),
                productName = it.product_name,
                productDescription = it.description,
                productQuantity = 1,
                totalPrice = it.price.toInt(),
                productPrice = it.price.toInt()
            )

            databaseHelper.insertData(cartItem)
            Toast.makeText(binding.root.context, "${it.product_name} added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadDetails(productId: Int) {
        val baseimgurl = "https://apolisrises.co.in/myshop/images/"
        detailModelView.getDetails(productId)
        detailModelView.productData.observe(this){
            with(binding){
                productName.text = it.product_name
                description.text = it.description
                Picasso.get().load(baseimgurl + it.product_image_url).error(R.drawable.defaultimage).into(productImage)
                productPrice.text = it.price

                specificationsRecyclerview.layoutManager = LinearLayoutManager(this@DetailsActivity)
                specificationsRecyclerview.adapter = SpecificationAdapter(it.specifications)


                reviewRecyclerview.layoutManager = LinearLayoutManager(this@DetailsActivity)
                reviewRecyclerview.adapter = ReviewAdapter(it.reviews)

            }

        }

        detailModelView.error.observe(this){

        }

    }


}