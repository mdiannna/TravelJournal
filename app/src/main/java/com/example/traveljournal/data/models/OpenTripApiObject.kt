package com.example.traveljournal.data.models

//import com.example.traveljournal.OpenTripApiLocation
import com.example.traveljournal.data.models.OpenTripApiFeature
import com.google.gson.annotations.SerializedName

data class OpenTripApiObject(
    @SerializedName("type")
    val type:String,
    @SerializedName("features")
    val features: List<OpenTripApiFeature>
)