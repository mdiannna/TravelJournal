package com.example.traveljournal.data.models

import com.example.traveljournal.views.Location
import com.google.gson.annotations.SerializedName

data class DataLocations (
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val locations:List<Location>

)