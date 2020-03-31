package com.example.traveljournal.data

import com.example.traveljournal.data.models.OpenTripApiObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*;

interface OpenTripApiInterface {
    @GET("/places/bbox")
    suspend fun getPlacesByCoordinates(@Query("lon_min") lngMin:Double,
                                       @Query("lat_min") latMin:Double,
                                       @Query("lon_max") lngMax:Double,
                                       @Query("lat_max") latMax:Double,
                                       @Query("format") format:String = "geojson",
                                       @Query("apikey") apikey:String,
                                       @Query("kinds") kinds:String): OpenTripApiObject
}
