package com.example.yelpapp.data.repository

import com.example.yelpapp.model.YelpSearchResult
import io.reactivex.Observable


interface SearchBusinessesRepository {
    fun getSearchBussiness(): Observable<YelpSearchResult>
}