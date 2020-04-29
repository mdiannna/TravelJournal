package com.example.traveljournal.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.traveljournal.data.LocationLiveData

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val locationData = LocationLiveData(application)

    fun getLocationData() = locationData
}