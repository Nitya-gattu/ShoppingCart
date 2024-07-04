package com.example.loginproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginproject.databinding.ShoppingGridItemBinding

class ShoppingItemAdapter(
    private val itemlist: List<ShoppingItem>
):RecyclerView.Adapter<ShoppingItemAdapter.MyViewHolder>() {

    private lateinit var itemBinding:ShoppingGridItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        itemBinding = ShoppingGridItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(itemBinding)
    }

    override fun getItemCount() = itemlist.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemlist[position])
    }

    inner class MyViewHolder(private val itemBinding: ShoppingGridItemBinding):
        RecyclerView.ViewHolder(itemBinding.root){
            fun bind(item: ShoppingItem){
                with(itemBinding){
                    smartPhone.setImageResource(item.shoppingitemImage)
                    txtsmartPhone.text = item.shoppinitemType


                    root.setOnClickListener{
                        val context= it.context
                        val intent = Intent(context, activity_total_billamount::class.java)
                        context.startActivity(intent)
                    }
                }
            }
    }

}