package com.example.traveljournal.viewmodels


import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveljournal.R
import com.example.traveljournal.data.APIServiceImpl
import com.example.traveljournal.data.LocationLiveData
import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.models.OpenTripDetailedObject
import com.google.android.gms.location.*

class CreateJournalViewModel(application: Application) : AndroidViewModel(application) {
    private val locationData = LocationLiveData(application)

    fun getLocationData() = locationData

//    lateinit var mFusedLocationClient: FusedLocationProviderClient

    //    TODO
private val mService  =  APIServiceImpl()
//    fun getAndroidData() : MutableLiveData<List<OpenTripDetailedObject>>? {
//    fun getAndroidData() : MutableLiveData<List<OpenTripApiObject>>? {
    fun getAndroidData() : OpenTripApiObject? {
        Log.e("getOpenTripApiObjectD","yes")

        var kinds = ""
        var latMax = 0.0
        var latMin = 0.0
        var lngMax = 0.0
        var lngMin = 0.0
        var lat = 0.0
        var lng = 0.0



        return mService.getPlacesByCoordinates( lngMin, latMin, lngMax,latMax, kinds)
    }

}