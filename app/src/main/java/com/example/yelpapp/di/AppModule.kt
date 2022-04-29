package com.example.yelpapp.di

import android.app.Application
import androidx.room.Room
import com.example.yelpapp.data.AndroidPermissionChecker
import com.example.yelpapp.data.PermissionChecker
import com.example.yelpapp.data.PlayServicesLocationDataSource
import com.example.yelpapp.data.database.BusinessDataBase
import com.example.yelpapp.data.database.model.Business
import com.example.yelpapp.data.datasource.BusinessLocalDataSource
import com.example.yelpapp.data.datasource.BusinessRemoteDataSource
import com.example.yelpapp.data.server.BusinessServerDataSource
import com.example.yelpapp.data.server.LocationDataSource
import com.example.yelpapp.model.BusinessRoomDataSource
import dagger.Binds
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

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule{

    @Binds
    abstract fun bindLocalDataSource(localDataSource: BusinessRoomDataSource) : BusinessLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: BusinessServerDataSource): BusinessRemoteDataSource

    @Binds
    abstract fun bindLocationDataSource(locationDataSource: PlayServicesLocationDataSource): LocationDataSource

    @Binds
    abstract fun bindPermissionChecker(permissionChecker: AndroidPermissionChecker): PermissionChecker

}