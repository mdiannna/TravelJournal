package com.example.traveljournal
//
//import retrofit2.Call;
//
//
//interface OpenTripApiInterface {
////    @GET("/places/bbox?lon_min={lngMin}&lat_min={latMin}&lon_max={lngMax}&lat_max={latMax}&" +
////            "kinds={kinds}&format=geojson&apikey={apikey}")
////
//    @GET("/places/bbox")
//    suspend fun getPlacesByCoordinates(@Query("lon_min") lngMin:Double,
//                                       @Query("lat_min") latMin:Double,
//                                       @Query("lon_max") lngMax:Double,
//                                       @Query("lat_max") latMax:Double,
//                                       @Query("format") format:String="geojson",
//                                       @Query("apikey") apikey:String,
//                                       @Query("kinds") kinds:String):OpenTripApiObject
//
//}