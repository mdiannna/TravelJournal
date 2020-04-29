package com.example.traveljournal.viewmodels
//https://developer.android.com/topic/libraries/architecture/coroutines

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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveljournal.R
import com.example.traveljournal.data.APIServiceImpl
import com.example.traveljournal.data.LocationLiveData
import com.example.traveljournal.data.models.OpenTripApiFeature
import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.models.OpenTripDetailedObject
import com.google.android.gms.location.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateJournalViewModel(application: Application) : AndroidViewModel(application) {

    private var response: MutableLiveData<OpenTripApiFeature>? = MutableLiveData()

    private val mService  =  APIServiceImpl()
    private val LOCATION_DELTA = 0.01

    fun getOpenTripApiObjData(lat:Double?, lng:Double?) : MutableLiveData<OpenTripApiFeature>? {
        Log.e("getOpenTripApiObjectD","yes")

        if(lat==null || lng==null || lat < 0.001  || lng < 0.001)  {
            return null
        }

        var lngMin = lng-LOCATION_DELTA
        var latMin = lat-LOCATION_DELTA
        var lngMax = lng+LOCATION_DELTA
        var latMax = lat+LOCATION_DELTA
        var kinds = "interesting_places"

//***************
// Tried Method 1
//***************
        GlobalScope.launch{
            kotlin.runCatching {
                mService.getPlacesByCoordinates(lngMin, latMin, lngMax, latMax,kinds)
            }.onSuccess{
               response = onPlacesFetched(it)
               print(it)
            }.onFailure{
                onPlacesFetchedError(it)
            }
        }

//***************
// Tried Method 2
//***************
//        response?.value = mService.getPlacesByCoordinates( lngMin, latMin, lngMax,latMax, kinds).features[0]

        return response
    }

    private fun onPlacesFetched(places: OpenTripApiObject):MutableLiveData<OpenTripApiFeature>? {
//      For easier debug:
        println("PLACES!!!")
        println("PLACES!!!")
        println("PLACES!!!")
        println(places)
        println(places.features)

        response?.value = places.features[0]
//        return places.features[0]

        return response
    }

    private fun onPlacesFetchedError(error: Throwable):String {
        println("ERROR!!!")
        println("ERROR!!!")
        println("ERROR!!!")
        print(error)

        return "Error:" + error
    }

}