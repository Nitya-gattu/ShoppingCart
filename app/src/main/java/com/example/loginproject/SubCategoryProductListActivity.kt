package com.example.loginproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginproject.data.subcategoryproducts.GetProductsListResponse
import com.example.loginproject.data.subcategoryproducts.Product
import com.example.loginproject.databinding.ActivitySubCategoryProductListBinding
import com.example.loginproject.productadapter.productlistbysubcat.ProductsListAdapter
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubCategoryProductListActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubCategoryProductListBinding
    val apiService = ApiClient.retrofit.create(ApiService::class.java)
    lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySubCategoryProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        databaseHelper = DatabaseHelper(this)

        val subcategoryId = intent.getStringExtra("subCategoryId") ?: return
        loadProductList(subcategoryId)

    }

    private fun loadProductList(subcategoryId: String) {

        val call: Call<GetProductsListResponse> = apiService.getProductsbySubCat(subcategoryId)

        call.enqueue(object: Callback<GetProductsListResponse>{
            override fun onResponse(
                call: Call<GetProductsListResponse>,
                response: Response<GetProductsListResponse>
            ) {
                if(! response.isSuccessful){
                    Toast.makeText(this@SubCategoryProductListActivity, "response not successfull" , Toast.LENGTH_LONG).show()
                    return
                }

                val productslist = response.body()

                if(productslist == null){
                    Toast.makeText(this@SubCategoryProductListActivity, "empty response from server" , Toast.LENGTH_LONG).show()
                    return
                }

                if(productslist.status != 0){
                    Toast.makeText(this@SubCategoryProductListActivity, "status not 0" , Toast.LENGTH_LONG).show()
                    return
                }
                try{
                    binding.tvCategoryname.text = productslist.products[0].category_name ?: "List Of Subcategory"
                }catch (e: Exception){
                    Toast.makeText(this@SubCategoryProductListActivity, "Empty List of products" , Toast.LENGTH_LONG).show()
                }
                val adapter = ProductsListAdapter(productslist.products, databaseHelper)
                binding.recyclerView.adapter = adapter


            }

            override fun onFailure(p0: Call<GetProductsListResponse>, p1: Throwable) {
                Toast.makeText(this@SubCategoryProductListActivity, "response failure" , Toast.LENGTH_LONG).show()
            }

        })

    }
}