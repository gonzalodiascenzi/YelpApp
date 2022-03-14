package com.example.yelpapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yelpapp.R
import com.example.yelpapp.databinding.ViewBusinessItemBinding
import com.example.yelpapp.model.Business
import com.example.yelpapp.ui.common.basicDiffUtil

class BusinessesAdapter (
    private val listener: (Business) -> Unit)
    : ListAdapter<Business, BusinessesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_business_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val business = getItem(position)
        holder.bind(business)
        holder.itemView.setOnClickListener { listener(business) }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewBusinessItemBinding.bind(view)
        fun bind(business: Business) {
            Glide
                .with(binding.root.context)
                .load(business.image_url)
                .into(binding.cover)
            binding.title.text = business.name
            binding.ratingBar.rating = business.rating.toFloat()
            binding.tvRating.text = business.rating.toString()
            binding.tvPhone.text = business.phone
            binding.tvAddress.text = business.location.address1
            binding.tvCity.text = business.location.city
        }
    }
}

