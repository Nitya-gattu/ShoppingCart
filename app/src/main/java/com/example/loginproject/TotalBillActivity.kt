package com.example.loginproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginproject.databinding.ActivityTotalBillamountBinding

class TotalBillActivity : AppCompatActivity() {
    private lateinit var totalBillamountBinding: ActivityTotalBillamountBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var databaseProductAdapter: DatabaseProductAdapter
    var products = mutableListOf<ProductAttributes>()
    val dproducts = mutableListOf<DatabaseProduct>()
    val productAttributeMap = mutableMapOf<Int,ProductAttributes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        totalBillamountBinding = ActivityTotalBillamountBinding.inflate(layoutInflater)
        setContentView(totalBillamountBinding.root)


        //initializing databasehelper
        databaseHelper = DatabaseHelper(this)
        addProducts()
        if(databaseHelper.readData().isEmpty()){
            products.forEach {product->
                databaseHelper.insertData(DatabaseProduct(product.productId, product.productPrice,product.productQuantity, (product.productPrice* product.productQuantity)))
            }
        }

        dproducts.addAll(databaseHelper.readData())
       // databaseProductAdapter = DatabaseProductAdapter()

        initviews()



    }

    private fun initviews() {
        totalBillamountBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        databaseProductAdapter =DatabaseProductAdapter(products, databaseHelper){product, newQuantity ->
            updateProductQuantity(product,newQuantity)

        }

        totalBillamountBinding.recyclerView.adapter = databaseProductAdapter
    }

    private fun updateProductQuantity(product: ProductAttributes , newQuantity:Int){
        val list = databaseHelper.readData()
        var newPrice = 0
        list.forEach{
            newPrice+= it.productPrice*it.productQuantity
        }
        val updatedProduct= product.copy(productQuantity = newQuantity)
        val position = products.indexOf(product)
        products[position] = updatedProduct
        databaseHelper.updateData(DatabaseProduct(product.productId, product.productPrice, newQuantity, (product.productPrice * newQuantity)))
        databaseProductAdapter.notifyItemChanged(position)
        updateTotalAmount(newPrice)
    }

    private fun updateTotalAmount(newPrice : Int) {
        val totalAmount = newPrice
        totalBillamountBinding.totalBill.text = "$ $totalAmount"

    }


    private fun addProducts() {
       products= mutableListOf(
                ProductAttributes(
                    productName = "RealMe Narzo 50",
                    productId = 1,
                    productDescription = "RealMe Narzo 50 (Speed Blue, 4GB RAM+64GB Storage) Helio G96 Processor | 50MP AI Triple Camera",
                    productPrice = 200,
                    totalProductPrice = 200,
                    unit = "Unit Price",
                    productQuantity = 1,
                    productImage = R.drawable.realmenarzo50
                ),
                ProductAttributes(
                    productName = "Redmi Note 11T",
                    productId = 2,
                    productDescription = "Redmi Note 11T 5G (Matte Black 6GB RAM 128GB ROM) | Dimensity 810 5G | 33W Pro Fast Charging",
                    productPrice = 220,
                    totalProductPrice = 440,
                    unit = "Unit Price",
                    productQuantity = 2,
                    productImage = R.drawable.realmenarzo50
                ),
                ProductAttributes(
                    productName = "RealMe Narzo 70",
                    productId = 3,
                    productDescription = "RealMe Narzo 50 (Speed Blue, 4GB RAM+64GB Storage) Helio G96 Processor | 50MP AI Triple Camera",
                    productPrice = 200,
                    totalProductPrice = 200,
                    unit = "Unit Price",
                    productQuantity = 1,
                    productImage = R.drawable.realmenarzo50
                ),
                ProductAttributes(
                    productName = "Redmi Note 5",
                    productId = 4,
                    productDescription = "Redmi Note 11T 5G (Matte Black 6GB RAM 128GB ROM) | Dimensity 810 5G | 33W Pro Fast Charging",
                    productPrice = 220,
                    totalProductPrice = 440,
                    unit = "Unit Price",
                    productQuantity = 2,
                    productImage = R.drawable.realmenarzo50
                )
            )

    }


}