package com.example.yelpapp

import android.app.Application
import androidx.room.Room
import com.example.yelpapp.model.database.YelpDataBase

class App : Application() {

    lateinit var db: YelpDataBase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            YelpDataBase::class.java, "business-db"
        ).build()
    }
}