package com.example.yelpapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.yelpapp.R
import com.example.yelpapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
    }
}