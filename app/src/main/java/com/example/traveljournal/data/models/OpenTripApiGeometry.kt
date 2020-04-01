package com.example.traveljournal.data.models

import com.google.gson.annotations.SerializedName

data class OpenTripApiGeometry(
    @SerializedName("type")
    val type:String,
    @SerializedName("coordinates")
    val coordinates:List<Double>
)