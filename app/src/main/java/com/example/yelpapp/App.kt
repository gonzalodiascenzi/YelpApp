package com.example.yelpapp

import android.app.Application
import androidx.room.Room
import com.example.yelpapp.data.database.YelpDataBase

class App: Application() {

    lateinit var db: YelpDataBase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            YelpDataBase::class.java, "yelp-db"
        ).build()
    }
}