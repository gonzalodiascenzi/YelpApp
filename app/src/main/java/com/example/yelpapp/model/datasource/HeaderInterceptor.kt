package com.example.yelpapp.model.datasource

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    private val token = "R1R9DOL09jglEGD1X_uu0nwvLAd0JhiLujEZqFfa9YwJjroOHFI_PsHiUFUwkDrlyMq4PHmOouElxOCMgaOlhEa2Cymwl4k2LU09ytvNMomlwbd0sQCeuINLcMQWYnYx"
    val headerNameAccept = "Accept"
    val haderNameAuthorization = "Authorization"
    val headerValueAccept = "application/json"
    val headerValueAuthorization = "Bearer $token"

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(headerNameAccept, headerValueAccept)
        requestBuilder.addHeader(haderNameAuthorization, headerValueAuthorization)
        return chain.proceed(requestBuilder.build())
    }
}