package com.example.loginproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginproject.databinding.ActivityProductBinding

class DatabaseProductAdapter (
    private var product:List<DatabaseProduct>,
    private val databaseHelper: DatabaseHelper,
    private val onQuantityChange: (DatabaseProduct, Int) ->Unit
):RecyclerView.Adapter<DatabaseProductAdapter.MyViewHolder>(){
    private lateinit var productBinding: ActivityProductBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        productBinding = ActivityProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(productBinding)
    }

    override fun getItemCount()= product.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(product[position])
    }
    fun updateProduct(updatedProduct: DatabaseProduct){
        val index = product.indexOfFirst { it.productId == updatedProduct.productId }
        if(index!=-1){
            product = product.toMutableList().apply {
                this[index] = updatedProduct
                notifyItemChanged(index)
            }
        }

    }


    inner class MyViewHolder(private val productBinding: ActivityProductBinding):
        RecyclerView.ViewHolder(productBinding.root){
            fun bind(item: DatabaseProduct){

                with(productBinding){
                    productName.text = item.productName
                    productPrice.text = "$ ${databaseHelper.readData()[item.productId-1].productPrice * databaseHelper.readData()[item.productId-1].productQuantity}"
                    productDiscription.text = item.productDescription
                    productunit.text = "Unit Price"
                    productunitPrice.text = "$ ${item.productPrice}"
                    productQuantity.text = databaseHelper.readData()[item.productId-1].productQuantity.toString()
                    //productImage.setImageResource(item.productImage)

//                    productAdd.setOnClickListener{
//                        val newQuantity= databaseHelper.readData()[item.productId-1].productQuantity + 1
//                        databaseHelper.updateData(DatabaseProduct(item.productId-1, item.productPrice, newQuantity, (item.productPrice* newQuantity)))
//                        productQuantity.text = databaseHelper.readData()[item.productId-1].productQuantity.toString()
//                        onQuantityChange(item , newQuantity)
//                    }

                    productAdd.setOnClickListener {
                        val newQuantity = item.productQuantity +1
                        onQuantityChange(item,newQuantity)
                    }

                    productSub.setOnClickListener {
                        val newQuantity = if(item.productQuantity>1) item.productQuantity -1 else 1
                        onQuantityChange(item, newQuantity)
                    }

//                    productSub.setOnClickListener{
//                        val newQuantity  =
//                            if(databaseHelper.readData()[item.productId-1].productQuantity>1) {
//                                databaseHelper.readData()[item.productId-1].productQuantity - 1
//                        }else { 1 }
//                        databaseHelper.updateData(DatabaseProduct(item.productId-1, item.productPrice, newQuantity, (item.productPrice* newQuantity)))
//                        productQuantity.text = databaseHelper.readData()[item.productId-1].productQuantity.toString()
//                        onQuantityChange(item , newQuantity)
//                    }

                }



            }

    }
}