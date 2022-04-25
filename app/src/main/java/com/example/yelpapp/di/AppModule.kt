package com.example.yelpapp.di

import android.app.Application
import androidx.room.Room
import com.example.yelpapp.data.database.BusinessDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(
            app,
            BusinessDataBase::class.java,
            "business-db"
        ).build()

    @Provides
    @Singleton
    fun provideBusinessDao(db: BusinessDataBase) =
        db.businessDao()

}