package com.example.loginproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loginproject.data.GetProductsCategory
import com.example.loginproject.databinding.ActivityHomeActivityBinding
import com.example.loginproject.productadapter.ShoppingItemAdapter
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeActivityBinding
    val apiservice = ApiClient.retrofit.create(ApiService::class.java)
    lateinit var shoppingitemAdapter: ShoppingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadProducts()
        binding.search.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.searchContainer, SearchFragment()).commit()
        }
    }

    private fun loadProducts() {
       val userEmail = SecuredSharedPrefference.getString(SecuredSharedPrefference.USER_EMAIL)
        binding.userEmail.text = "Welcome $userEmail"

        binding.logout.setOnClickListener{

            showLogoutDialog()


        }

        val call:Call<GetProductsCategory> = apiservice.getProductCategory()

        call.enqueue(object:Callback<GetProductsCategory>{
            override fun onResponse(
                call: Call<GetProductsCategory>,
                response: Response<GetProductsCategory>
            ) {
                if(!response.isSuccessful){
                    Toast.makeText(this@HomeActivity, "not sucessful", Toast.LENGTH_LONG).show()
                    return
                }

                val productcategory = response.body()
                if(productcategory == null){
                    Toast.makeText(this@HomeActivity, "empty response from server", Toast.LENGTH_LONG).show()
                    return
                }

                if(productcategory.status != 0){
                    Toast.makeText(this@HomeActivity, "status not 0", Toast.LENGTH_LONG).show()
                }


                with(binding){


                    recyclerView.layoutManager = GridLayoutManager(this@HomeActivity, 2)
                    shoppingitemAdapter = ShoppingItemAdapter(productcategory.categories)
                    recyclerView.adapter = shoppingitemAdapter
                }


            }

            override fun onFailure(p0: Call<GetProductsCategory>, p1: Throwable) {
                p1.printStackTrace()
                Toast.makeText(this@HomeActivity, "unknown error", Toast.LENGTH_LONG).show()
            }

        })




    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Logout")
            setMessage("are you sure you want to logout?")
            setPositiveButton("Sure"){_,_ ->
                SecuredSharedPrefference.clearAllPreference()
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                finish()
            }

            setNegativeButton("cancel"){dialog,_ ->
                dialog.dismiss()
            }
        }

        val alertDialog:AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}