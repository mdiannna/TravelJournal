package com.example.traveljournal.data.models

import com.example.traveljournal.OpenTripApiLocation
import com.example.traveljournal.OpenTripApiGeometry
import com.google.gson.annotations.SerializedName

data class OpenTripApiFeature(
    @SerializedName("type")
    val type:String,
    @SerializedName("id")
    val id:String,
    @SerializedName("geometry")
    val geometry: OpenTripApiGeometry,

    @SerializedName("properties")
    val properties: OpenTripApiLocation
)