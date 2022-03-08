package com.example.yelpapp.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yelpapp.R
import com.example.yelpapp.databinding.ViewBusinessItemBinding
import com.example.yelpapp.model.Businesses
import com.example.yelpapp.ui.common.basicDiffUtil
import com.example.yelpapp.ui.common.inflate
import com.example.yelpapp.ui.common.loadUrl

class BusinessesAdapter (
    private val listener: (Businesses) -> Unit)
    : ListAdapter<Businesses, BusinessesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_business_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val business = getItem(position)
        holder.bind(business)
        holder.itemView.setOnClickListener { listener(business) }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewBusinessItemBinding.bind(view)
        fun bind(businesses: Businesses) {
            binding.cover.loadUrl(businesses.imageUrl)
            binding.title.text = businesses.title
        }
    }
}

