package com.example.traveljournal

import com.google.gson.annotations.SerializedName

data class OpenTripApiObject(
    @SerializedName("type")
    val type:String,
    @SerializedName("id")
    val id:String,
//    @SerializedName("geometry")

    @SerializedName("properties")
    val properties: OpenTripApiLocation
)