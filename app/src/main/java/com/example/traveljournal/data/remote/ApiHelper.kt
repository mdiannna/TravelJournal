package com.example.traveljournal.data.remote

import okhttp3.OkHttpClient
import android.util.Log
import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.models.OpenTripDetailedObject
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {
    private val BASE_URL = "http://api.opentripmap.com/0.1/en/"
    private val API_KEY = "5ae2e3f221c38a28845f05b6eab28f2de056a215a99556e91c9be261"

    val okHttpClient = OkHttpClient().newBuilder().addInterceptor(getInterceptor()).build()
    lateinit var apiService: OpenTripApiInterface

    init {
        makeService()
    }

    private fun makeService() {
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.apiService = retrofit.create(OpenTripApiInterface::class.java)
    }


    suspend fun getPlacesByCoordinatesRequest(
        lngMin: Double,
        latMin: Double,
        lngMax: Double,
        latMax: Double,
        kinds: String
//        format: String, // has default value
    ): Result<OpenTripApiObject> {
        return safeApiCall(
            call = { apiService.getPlacesByCoordinates(lngMin, latMin, lngMax, latMax, API_KEY, kinds) })
    }

//    TODO:
//    override suspend fun  getPlacesDetailedInfo(id:String): OpenTripDetailedObject {
//        println("API_KEY")
//        println(API_KEY)
////        return apiService.getPlacesDetailedInfo(API_KEY, id)
//        return apiService.getPlacesDetailedInfo(API_KEY)
//    }


    private fun getInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }


    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                Result.Success(myResp.body()!!)
            } else {

                /*
                handle standard error codes
                if (myResp.code() == 403){
                    Log.i("responseCode","Authentication failed")
                }
                .
                .
                .
                 */

                Result.Error(myResp.errorBody()?.string() ?: "Something goes wrong")
            }

        } catch (e: Exception) {
            Result.Error(e.message ?: "Internet error runs")
        }
    }


}