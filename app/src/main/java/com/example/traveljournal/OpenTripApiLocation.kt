package com.example.traveljournal
import com.google.gson.annotations.SerializedName;

data class OpenTripApiLocation (
    @SerializedName("xid")
    val id: String,
    @SerializedName("name")
    val name:String,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("kinds")
    val kinds:String,
    @SerializedName("osm")
    val osm: String
)