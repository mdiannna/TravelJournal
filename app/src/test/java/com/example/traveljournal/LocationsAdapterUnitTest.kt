package com.example.traveljournal

import android.app.Application
import com.example.traveljournal.data.models.Location
import com.example.traveljournal.viewmodels.TopLocationsViewModel
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class LocationsAdapterUnitTest {

    @Mock
    var application = Application()

    @Test
    fun updateLocations_isCorrect() {
        var initialLocation = Location(
            1, "Location1", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F, "no image"
        )

        var newLocation = Location(
            1, "NewLocation", "Romania", "str. Stefan cel Mare",
            "Interesting new location", 21, 51, 5F, "no image"
        )


        var newLocationList:ArrayList<Location> = ArrayList<Location>()
        newLocationList.add(initialLocation)
        newLocationList.add(newLocation)


        var newLocations = arrayOf(initialLocation, newLocation)

        val viewModel = TopLocationsViewModel(application)

        viewModel.initializeLocationList()
        viewModel.addLocations(newLocations)

        assertEquals(viewModel.getLocationList(), newLocationList)
    }

    @Test
    fun addTopLocations_isCorrect() {
        val viewModel = TopLocationsViewModel(application)

        viewModel.addTopLocations()

        assertNotNull(viewModel.getTopLocations())
    }


}
