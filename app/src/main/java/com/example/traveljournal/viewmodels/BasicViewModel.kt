package com.example.traveljournal.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.models.OpenTripApiFeature
import com.example.traveljournal.views.BaseNavigator

import java.lang.ref.WeakReference

abstract class BasicViewModel<N : BaseNavigator> : ViewModel() {

    val loading : MutableLiveData<OpenTripApiFeature> by lazy {
        MutableLiveData<OpenTripApiFeature>()
    }

    lateinit var navigator: WeakReference<N>

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator(): N? {
        return navigator.get()
    }


}