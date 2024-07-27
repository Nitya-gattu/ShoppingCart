package com.example.loginproject.productadapter.productlistbysubcat

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.DatabaseHelper
import com.example.loginproject.DatabaseProduct
import com.example.loginproject.R
import com.example.loginproject.SubCategoryActivity
import com.example.loginproject.data.subcategoryproducts.Product
import com.example.loginproject.databinding.ActivitySubcategoryitemBinding
import com.example.loginproject.subcategory.details.DetailsActivity
import com.squareup.picasso.Picasso

class ProductsListViewHolder(
    val binding: ActivitySubcategoryitemBinding,
    val databaseHelper: DatabaseHelper
):ViewHolder(binding.root) {

    val baseimgurl = "https://apolisrises.co.in/myshop/images/"
    fun bind(product: Product, databaseHelper: DatabaseHelper){

        with(binding){
            productName.text = product.product_name
            productDiscription.text = product.description
            productunitPrice.text = product.price
            addToCart.text = "Add To Cart"

            val imageurl = baseimgurl + product.product_image_url
            Picasso.get().load(imageurl).error(R.drawable.defaultimage).into(productImage)

            binding.addToCart.setOnClickListener{
                val cartItem = DatabaseProduct(
                    productId = product.product_id.toInt(),
                    productName = product.product_name,
                    productDescription = product.description,
                    productQuantity = 1,
                    totalPrice = product.price.toInt(),
                    productPrice = product.price.toInt()
                )

                databaseHelper.insertData(cartItem)
                Toast.makeText(binding.root.context, "${product.product_name} added to cart", Toast.LENGTH_SHORT).show()
            }

            root.setOnClickListener {
                val context = it.context
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("productId" , product.product_id)
                context.startActivity(intent)
            }

        }

    }

}