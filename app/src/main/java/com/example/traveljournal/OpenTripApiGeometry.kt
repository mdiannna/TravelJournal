package com.example.traveljournal

import com.google.gson.annotations.SerializedName

data class OpenTripApiGeometry(
    @SerializedName("type")
    val type:String,
    @SerializedName("coordinates")
    val coordinates:ArrayList<Double>
)