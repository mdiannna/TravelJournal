package com.example.traveljournal.viewmodels
//Reading list:
//https://developer.android.com/topic/libraries/architecture/coroutines
//https://medium.com/@keyvan.nrz/from-coroutines-live-data-mvvm-and-retrofit-is-anyone-hear-me-7285d941a7cd
//https://github.com/keyvanNorouzi/coroutinesKoinRetrofitMVVM
//https://github.com/lmorda/kotlin-mvvm-coroutines-retrofit/blob/master/app/src/main/java/com/example/lmorda/myapplication/viewmodel/ReposViewModel.kt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveljournal.data.models.OpenTripApiFeature

class CreateJournalPageViewModel : ViewModel() {

    val response: MutableLiveData<OpenTripApiFeature> by lazy {
        MutableLiveData<OpenTripApiFeature>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
//
//    fun getPlacesByCoordinatesFromServer() {
//        val  lat = 46.2
//        val lng = 23.4
//        if (lat == null || lng == null || lat == 0.0|| lng == 0.0) {
//            return
//        }
//
//
//        var lngMin = lng - LOCATION_DELTA
//        var latMin = lat - LOCATION_DELTA
//        var lngMax = lng + LOCATION_DELTA
//        var latMax = lat + LOCATION_DELTA
//        var kinds = "interesting_places"
//
//        viewModelScope.launch {
//            val retrofitObj =
//                ApiHelper.getPlacesByCoordinatesRequest(lngMin, latMin, lngMax, latMax, kinds)
//                Log.d("TEST<COROUTINE", "test coroutine")
//            when (retrofitObj) {
//                is Result.Success -> response.postValue(retrofitObj.data.features[0])
//                is Result.Error -> {
//                    errorMessage.postValue(retrofitObj.exception)
//                }
//            }
//        }
//    }
}
