package com.example.traveljournal.data

import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.models.OpenTripDetailedObject

interface APIService {
    suspend fun getPlacesByCoordinates(lngMin:Double,
                                       latMin:Double,
                                       lngMax:Double,
                                       latMax:Double,
                                       kinds:String): OpenTripApiObject

    suspend fun getPlacesDetailedInfo(id: String): OpenTripDetailedObject
}