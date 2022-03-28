package com.example.yelpapp.data.repository

import com.example.yelpapp.data.entity.YelpSearchResult
import io.reactivex.Observable


interface SearchBusinessesRepository {
    fun getSearchBussiness(): Observable<YelpSearchResult>
}