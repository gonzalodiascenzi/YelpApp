package com.example.yelpapp.data.server


import arrow.core.Either
import com.example.yelpapp.data.database.BusinessDao
import com.example.yelpapp.data.datasource.BusinessRemoteDataSource
import com.example.yelpapp.data.entity.RemoteBusiness
import com.example.yelpapp.data.tryCall
import com.example.yelpapp.domain.Business
import com.example.yelpapp.domain.Error
import javax.inject.Inject


class BusinessServerDataSource @Inject constructor() : BusinessRemoteDataSource {

    override suspend fun searchBusiness(region: Pair<String, String>): Either<Error, List<Business>> = tryCall {
        RemoteConnection.service
            .searchBusinesses(region.first, region.second)
            .businesses.map { it.toDomainModel() }
    }
}

private fun RemoteBusiness.toDomainModel(): Business =
    Business(
        alias,
        display_phone,
        distance,
        id,
        image_url,
        is_closed,
        address.toString(),
        city.toString(),
        name,
        phone,
        price ?: "",
        rating ?: 0.0,
        review_count,
        transactions,
        url
    )