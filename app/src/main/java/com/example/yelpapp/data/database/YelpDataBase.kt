package com.example.yelpapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.yelpapp.data.database.model.Business
import com.example.yelpapp.data.database.model.Category

@Database(entities = [Business::class, Category::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class YelpDataBase: RoomDatabase() {

    abstract fun businessDao(): BusinessDao
}