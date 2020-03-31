package com.example.traveljournal.data

import com.example.traveljournal.data.models.OpenTripApiObject

interface APIService {
    suspend fun getPlacesByCoordinates(lngMin:Double,
                                       latMin:Double,
                                       lngMax:Double,
                                       latMax:Double,
                                       format:String = "geojson",
                                       kinds:String): OpenTripApiObject
}