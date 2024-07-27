package com.example.loginproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginproject.data.subcategory.SubcategoryResponse
import com.example.loginproject.databinding.ActivitySubCategoryBinding
import com.example.loginproject.productadapter.subcategory.SubCategotytypeAdapter
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubCategoryBinding
    val apiservice = ApiClient.retrofit.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryId = intent.getStringExtra("categoryId" ) ?: return
        val categoryName = intent.getStringExtra("subCategoryName") ?: "Sub Category"
        binding.loginheader.text = categoryName
        loadSubCategory(categoryId)



    }

    private fun loadSubCategory(categoryId: String) {

        val call: Call<SubcategoryResponse> = apiservice.getSubCategory(categoryId)
        call.enqueue(object : Callback<SubcategoryResponse>{
            override fun onResponse(
                call: Call<SubcategoryResponse>,
                response: Response<SubcategoryResponse>
            ) {

                if(!response.isSuccessful){
                    Toast.makeText(this@SubCategoryActivity, "error not successfull" , Toast.LENGTH_LONG).show()
                    return
                }

                val subcategory = response.body()?.subcategories

                if(subcategory == null){
                    Toast.makeText(this@SubCategoryActivity, "empty response from server" , Toast.LENGTH_LONG).show()
                    return
                }

                binding.recyclerview.layoutManager = GridLayoutManager(this@SubCategoryActivity, 2)

                //Toast.makeText(this@SubCategoryActivity, "hello" , Toast.LENGTH_LONG).show()

                val adapter = SubCategotytypeAdapter(subcategory)
                binding.recyclerview.adapter = adapter


            }

            override fun onFailure(p0: Call<SubcategoryResponse>, p1: Throwable) {
                p1.printStackTrace()
                Toast.makeText(this@SubCategoryActivity, "unknown error" , Toast.LENGTH_LONG).show()
            }

        })
    }
}