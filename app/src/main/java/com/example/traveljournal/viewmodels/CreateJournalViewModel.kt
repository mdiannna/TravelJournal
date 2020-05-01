package com.example.traveljournal.viewmodels
//https://developer.android.com/topic/libraries/architecture/coroutines
//https://medium.com/@keyvan.nrz/from-coroutines-live-data-mvvm-and-retrofit-is-anyone-hear-me-7285d941a7cd
//https://github.com/keyvanNorouzi/coroutinesKoinRetrofitMVVM

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveljournal.data.remote.APIServiceImpl
import com.example.traveljournal.data.models.OpenTripApiFeature
import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.remote.ApiHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.traveljournal.data.remote.Result

class CreateJournalViewModel : ViewModel() {

    private val LOCATION_DELTA = 0.01
    val response: MutableLiveData<OpenTripApiFeature> = MutableLiveData<OpenTripApiFeature>()
    val errorMessage: MutableLiveData<String> = MutableLiveData<String>()

    fun getPlacesByCoordinatesFromServer(lat: Double?, lng: Double?) {
        if (lat == null || lng == null || lat == 0.0|| lng == 0.0) {
            return
        }

        var lngMin = lng - LOCATION_DELTA
        var latMin = lat - LOCATION_DELTA
        var lngMax = lng + LOCATION_DELTA
        var latMax = lat + LOCATION_DELTA
        var kinds = "interesting_places"

        viewModelScope.launch {
            val retrofitObj =
                ApiHelper.getPlacesByCoordinatesRequest(lngMin, latMin, lngMax, latMax, kinds)

            when (retrofitObj) {
                is Result.Success -> response.postValue(retrofitObj.data.features[0])
                is Result.Error -> {
                    errorMessage.postValue(retrofitObj.exception)
                }
            }
        }
    }
}


//    private val mService  =
//        APIServiceImpl()

//
//    fun getOpenTripApiObjData(lat:Double?, lng:Double?) : MutableLiveData<OpenTripApiFeature>? {
//        Log.e("getOpenTripApiObjectD","yes")
//
//        if(lat==null || lng==null || lat < 0.001  || lng < 0.001)  {
//            return null
//        }
//
//        var lngMin = lng-LOCATION_DELTA
//        var latMin = lat-LOCATION_DELTA
//        var lngMax = lng+LOCATION_DELTA
//        var latMax = lat+LOCATION_DELTA
//        var kinds = "interesting_places"
//
////***************
//// Tried Method 1
////***************
//        GlobalScope.launch{
//            kotlin.runCatching {
//                mService.getPlacesByCoordinates(lngMin, latMin, lngMax, latMax,kinds)
//            }.onSuccess{
//               response = onPlacesFetched(it)
//               print(it)
//            }.onFailure{
//                onPlacesFetchedError(it)
//            }
//        }
//
////***************
//// Tried Method 2
////***************
////        response?.value = mService.getPlacesByCoordinates( lngMin, latMin, lngMax,latMax, kinds).features[0]
//
//        return response
//    }
//
//    private fun onPlacesFetched(places: OpenTripApiObject):MutableLiveData<OpenTripApiFeature>? {
////      For easier debug:
//        println("PLACES!!!")
//        println("PLACES!!!")
//        println("PLACES!!!")
//        println(places)
//        println(places.features)
//
//        response?.value = places.features[0]
////        return places.features[0]
//
//        return response
//    }
//
//    private fun onPlacesFetchedError(error: Throwable):String {
//        println("ERROR!!!")
//        println("ERROR!!!")
//        println("ERROR!!!")
//        print(error)
//
//        return "Error:" + error
//    }

//}