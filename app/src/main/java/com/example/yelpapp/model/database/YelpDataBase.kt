package com.example.yelpapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Business::class, Category::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class YelpDataBase: RoomDatabase() {

    abstract fun businessDao(): BusinessDao
}