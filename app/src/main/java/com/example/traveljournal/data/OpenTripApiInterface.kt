package com.example.traveljournal.data

import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.models.OpenTripDetailedObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*;

interface OpenTripApiInterface {
    @GET("places/bbox")
    suspend fun getPlacesByCoordinates(@Query("lon_min") lngMin:Double,
                                       @Query("lat_min") latMin:Double,
                                       @Query("lon_max") lngMax:Double,
                                       @Query("lat_max") latMax:Double,
                                       @Query("apikey") apikey:String,
                                       @Query("kinds") kinds:String,
                                       @Query("format") format:String = "geojson"): OpenTripApiObject
//    Example request:
//    http://api.opentripmap.com/0.1/ru/places/xid/W206834774?apikey=API_KEY
//    http://api.opentripmap.com/0.1/ru/places/xid/N5259791626?apikey=API_KEY
//    TODO: request with correct path parameter
//    @GET("places/xid/{Id}")
    @GET("places/xid/W206834774")
    suspend fun getPlacesDetailedInfo(
//        @Path("Id") id:String,
        @Query("apikey") apikey:String
        ): OpenTripDetailedObject


    // WARNING: this POST request doesn't exist on the OpenTripMapApi, it is only a demo how to write POST request
    @POST("place/create/new")
    suspend fun createNewPlace(@Body place:OpenTripDetailedObject)

    // WARNING: this POST request doesn't exist on the OpenTripMapApi, it is only a demo how to write POST request
    @POST("place/update/{id}")
    suspend fun createUpdatePlace(
        @Path("id") id:String,
        @Body place:OpenTripDetailedObject
    )

}
