package com.example.traveljournal.data

import com.example.traveljournal.data.models.OpenTripApiObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Implementation of APIService interface
class APIServiceImpl: APIService {
    val BASE_URL = "http://api.opentripmap.com/0.1/ru/"
    private val API_KEY = "5ae2e3f221c38a28845f05b6eab28f2de056a215a99556e91c9be261"

    private val apiService = createApiService(
        BASE_URL,
        OpenTripApiInterface::class.java
    )

    private fun <T> createApiService(baseURL: String, service: Class <T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(service)
    }

    override suspend fun getPlacesByCoordinates(
        lngMin: Double,
        latMin: Double,
        lngMax: Double,
        latMax: Double,
        format: String,
        kinds: String
    ): OpenTripApiObject {
        return apiService.getPlacesByCoordinates(lngMin, latMin, lngMax, latMax, format, API_KEY, kinds)
    }
}