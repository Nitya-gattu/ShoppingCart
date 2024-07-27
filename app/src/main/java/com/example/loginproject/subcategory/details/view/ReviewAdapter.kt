package com.example.loginproject.subcategory.details.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.loginproject.databinding.ReviewViewholderBinding
import com.example.loginproject.databinding.SpecificationViewholderBinding
import com.example.loginproject.subcategory.details.data.Review
import com.example.loginproject.subcategory.details.data.Specification

class ReviewAdapter(
    val reviewList: List<Review>
) :Adapter<ReviewAdapter.ReviewViewHolder>(){


        inner class ReviewViewHolder (val binding: ReviewViewholderBinding): ViewHolder(binding.root){
            fun bind(review: Review){
                with(binding){
                  fullname.text = review.full_name
                    title.text= review.review_title
                    txtReview.text = review.review
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
            val binding = ReviewViewholderBinding.inflate(LayoutInflater.from(parent.context), parent,false)
            return ReviewViewHolder(binding)
        }

        override fun getItemCount()= reviewList.size

        override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
            holder.bind(reviewList[position])
        }

}