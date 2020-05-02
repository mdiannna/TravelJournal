package com.example.traveljournal.viewmodels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.traveljournal.data.models.Location

class TopLocationsViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var locationList: ArrayList<Location>;

    fun getLocationList():ArrayList<Location> {
        if (!::locationList.isInitialized) {
            locationList = ArrayList<Location>()
            addLocations()
        }

        return locationList
    }
    fun addLocations() {
        var loc1 = Location(
            1, "Location1", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F, "no image"
        )
        var loc2 = Location(
            2, "Location2", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F, "no image"
        )
        var loc3 = Location(
            3, "White house", "USA", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F, "no image"
        )
        var loc4 = Location(
            4, "Orheiul Vechi", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F, "no image"
        )
        var loc5 = Location(
            5, "Louvre", "Paris", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F, "no image"
        )

        locationList = ArrayList<Location>(10);
        locationList.add(loc1)
        locationList.add(loc2)
        locationList.add(loc3)
        locationList.add(loc4)
        locationList.add(loc5)
    }
}