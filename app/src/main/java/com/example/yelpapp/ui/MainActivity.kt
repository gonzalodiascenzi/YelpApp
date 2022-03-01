package com.example.yelpapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yelpapp.R
import com.example.yelpapp.databinding.ActivityMainBinding
import com.example.yelpapp.model.Businesses

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = BusinessesAdapter(
            listOf(
                Businesses("Jorge", "Pizzeria", "Pizzeria Don Jorge"),
                Businesses("Hermanos Frings", "Comida Rapida", "Pollos Hermanos"),
                Businesses("Donald Trump", "Pizzeria", "Kentucky"),
                Businesses("Donato", "Restaurante", "Il Postino"),
                Businesses("Aluk Akhina", "Comida Arabe", "Le BakLon"),
                Businesses("Ricardo Fort", "Sangucheria", "Miami")
            )
        )
    }
}