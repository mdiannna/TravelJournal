package com.example.traveljournal.data.models

import com.google.gson.annotations.SerializedName

data class OpenTripApiObject(
    @SerializedName("type")
    val type:String,
    @SerializedName("features")
    val features: List<OpenTripApiFeature>
)