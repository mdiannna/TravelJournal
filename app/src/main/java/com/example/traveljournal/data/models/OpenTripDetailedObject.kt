package com.example.traveljournal.data.models

import com.google.gson.annotations.SerializedName

data class OpenTripDetailedObject (
    val name:String,
    @SerializedName("kinds")
    val kinds:String,
    val address: OpenTripAddress,
    @SerializedName("wikipedia_extracts")
    val wikipediaExtracts: OpenTripWikipedia,
    val image:String
    )