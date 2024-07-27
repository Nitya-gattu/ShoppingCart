package com.example.loginproject.subcategory.details.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.databinding.SpecificOrderViewholderBinding
import com.example.loginproject.databinding.SpecificationViewholderBinding
import com.example.loginproject.subcategory.details.data.Specification

class SpecificationAdapter(
val specificationlist: List<Specification>
) : Adapter<SpecificationAdapter.SpecificViewHolder>(){



    inner class SpecificViewHolder (val binding: SpecificationViewholderBinding): ViewHolder(binding.root){
        fun bind(specification: Specification){
            with(binding){
                title.text = specification.title
                txtSpecification.text = specification.specification
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificViewHolder {
        val binding = SpecificationViewholderBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return SpecificViewHolder(binding)
    }

    override fun getItemCount()= specificationlist.size

    override fun onBindViewHolder(holder: SpecificViewHolder, position: Int) {
        holder.bind(specificationlist[position])
    }
}