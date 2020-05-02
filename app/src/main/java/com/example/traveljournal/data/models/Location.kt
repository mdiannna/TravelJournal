package com.example.traveljournal.data.models
import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    // TODO: create class and change in the future
    @SerializedName("country")
    val country: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description:String,
    @SerializedName("lat")
    val lat: Int,
    @SerializedName("lng")
    val lng: Int,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("image")
    val image:String
)