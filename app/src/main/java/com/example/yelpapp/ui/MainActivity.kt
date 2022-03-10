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
    }
}