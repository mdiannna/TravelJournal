package com.example.traveljournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveljournal.Location

class TopLocationsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter:RecyclerView.Adapter<*>
    private lateinit var viewManager:RecyclerView.LayoutManager
    private lateinit var locationList: ArrayList<Location>;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_locations)

        addLocations()


        viewManager = LinearLayoutManager(this)
        viewAdapter = LocationListAdapter(locationList)

        recyclerView = findViewById<RecyclerView>(R.id.top_locations_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager

            adapter = viewAdapter
        }
    }

    fun addLocations() {
        var loc1 = Location(1, "Location1", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )
        var loc2 = Location(2, "Location2", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )
        var loc3 = Location(3, "Location3", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )
        var loc4 = Location(4, "Location4", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )
        var loc5 = Location(5, "Location5", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )

        locationList.add(loc1)
        locationList.add(loc2)
        locationList.add(loc3)
        locationList.add(loc4)
        locationList.add(loc5)
    }

}
