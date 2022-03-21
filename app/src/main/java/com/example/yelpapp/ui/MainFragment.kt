package com.example.yelpapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.yelpapp.R
import com.example.yelpapp.databinding.FragmentMainBinding
import com.example.yelpapp.model.datasource.YelpDbClient
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    val DEFAULT_LATITUD = "-31.417"
    val DEFAULT_LONGITUD = "-64.183"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)

        lifecycleScope.launch {
            val business =  YelpDbClient.service.searchBusinesses(DEFAULT_LATITUD ,DEFAULT_LONGITUD)
            Toast.makeText(requireContext(), business.toString(), Toast.LENGTH_SHORT).show()
        }


    }
}