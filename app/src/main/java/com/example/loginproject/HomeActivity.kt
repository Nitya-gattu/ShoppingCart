package com.example.loginproject

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loginproject.data.GetProductsCategory
import com.example.loginproject.databinding.ActivityHomeActivityBinding
import com.example.loginproject.orders.view.OrdersActivity
import com.example.loginproject.productadapter.ShoppingItemAdapter
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeActivityBinding
    val apiservice = ApiClient.retrofit.create(ApiService::class.java)
    lateinit var shoppingitemAdapter: ShoppingItemAdapter
    lateinit var call:Call<GetProductsCategory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fullName = intent.getStringExtra("FULL_NAME")
        val mobileNo = intent.getStringExtra("MOBILE_NO")

        loadProducts()
        initViews(fullName?:"", mobileNo?:"")
        binding.search.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.searchContainer, SearchFragment()).commit()
        }
    }

    private fun initViews(fullName:String, mobileNo:String ) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.menu)
        }

        //userEmail, name, mobile number setting on menu
        val userEmail = SecuredSharedPrefference.getString(SecuredSharedPrefference.USER_EMAIL)
        val navView:NavigationView = findViewById(R.id.navMenu)
        val menu = navView.menu
        val email = menu.findItem(R.id.email)
        email.title = userEmail

        val name = menu.findItem(R.id.name)
        name.title = "Welcome ${fullName}"

        val number = menu.findItem(R.id.number)
        number.title = "+1 ${mobileNo}"

        binding.navMenu.setNavigationItemSelectedListener {
            menuItems->
            menuItems.isChecked = true
            when(menuItems.itemId){
                R.id.menu_home ->{
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                R.id.menu_cart ->{
                    startActivity(Intent(this, TotalBillActivity::class.java))
                }
                R.id.menu_orders ->{
                    startActivity(Intent(this, OrdersActivity::class.java))

                }
                R.id.menu_logout ->{
                    showLogoutDialog()
                }
            }
            true
        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            if(binding.main.isDrawerOpen(GravityCompat.START)){
                binding.main.closeDrawer(GravityCompat.START)
            }else{
                binding.main.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun loadProducts() {

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