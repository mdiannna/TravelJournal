package com.example.traveljournal

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call;
import retrofit2.http.*;

interface OpenTripApiInterface {
//    @GET("/places/bbox?lon_min={lngMin}&lat_min={latMin}&lon_max={lngMax}&lat_max={latMax}&" +
//            "kinds={kinds}&format=geojson&apikey={apikey}")
//
    @GET("/places/bbox")
    suspend fun getPlacesByCoordinates(@Query("lon_min") lngMin:Double,
                                       @Query("lat_min") latMin:Double,
                                       @Query("lon_max") lngMax:Double,
                                       @Query("lat_max") latMax:Double,
                                       @Query("format") format:String="geojson",
                                       @Query("apikey") apikey:String,
                                       @Query("kinds") kinds:String):OpenTripApiObject

    private fun getPlacesByCoordinates(latMin:String, lngMin:String, latMax:String, lngMax:String, apikey:String, kinds:String) {
        GlobalScope.launch{
            kotlin.runCatching {
                apiService.getPlacesByCoordinates(latMin, lngMin, latMax, lngMax, apikey, kinds)
            }.onSuccess{
                print(it);
            }.onFailure{
                print(it)
            }
        }
    }


}