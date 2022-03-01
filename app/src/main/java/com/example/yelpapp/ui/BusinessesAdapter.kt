package com.example.yelpapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yelpapp.databinding.ViewBusinessItemBinding
import com.example.yelpapp.model.Businesses

class BusinessesAdapter (
    var businesses: List<Businesses>
        )
    :RecyclerView.Adapter<BusinessesAdapter.ViewHolder>(){
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewBusinessItemBinding.inflate(
            LayoutInflater
                .from(parent.context),
                 parent,
                 false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BusinessesAdapter.ViewHolder, position: Int) {
        val business = businesses[position]
        holder.bind(business)
    }

    override fun getItemCount(): Int {
        return businesses.size
    }


    class ViewHolder (private val binding: ViewBusinessItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(business: Businesses){
            binding.title.text = business.title
            Glide
                .with(binding.root.context)
                .load(binding.cover)
        }

    }


}

