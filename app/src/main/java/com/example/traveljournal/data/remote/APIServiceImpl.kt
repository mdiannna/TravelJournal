package com.example.traveljournal.data.remote

import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.models.OpenTripDetailedObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Implementation of APIService interface
class APIServiceImpl {
//    val BASE_URL = "http://api.opentripmap.com/0.1/ru/"
    val BASE_URL = "http://api.opentripmap.com/0.1/en/"
    private val API_KEY = "5ae2e3f221c38a28845f05b6eab28f2de056a215a99556e91c9be261"

    private val apiService = createApiService(
        BASE_URL,
        OpenTripApiInterface::class.java
    )

    private fun <T> createApiService(baseURL: String, service: Class <T>): T {
//        Logging interceptor - not working
//        var logging:HttpLoggingInterceptor = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
//        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClient.build())
            .build()
        return retrofit.create(service)
    }
//
////    override suspend fun getPlacesByCoordinates(
//    override  fun getPlacesByCoordinates(
//        lngMin: Double,
//        latMin: Double,
//        lngMax: Double,
//        latMax: Double,
//        kinds: String
////        format: String, // has default value
//   ): OpenTripApiObject {
//        return apiService.getPlacesByCoordinates(lngMin, latMin, lngMax, latMax, API_KEY, kinds)
//    }
//
//    override suspend fun  getPlacesDetailedInfo(id:String): OpenTripDetailedObject {
//        println("API_KEY")
//        println(API_KEY)
////        return apiService.getPlacesDetailedInfo(API_KEY, id)
//        return apiService.getPlacesDetailedInfo(API_KEY)
//    }
}