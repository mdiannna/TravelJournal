package com.example.traveljournal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.traveljournal.R

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveljournal.Location
import com.example.traveljournal.LocationListAdapter
import kotlinx.android.synthetic.main.fragment_top_locations.*


class TopLocationsFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter:RecyclerView.Adapter<*>
    private lateinit var viewManager:RecyclerView.LayoutManager
    private lateinit var locationList: ArrayList<Location>;

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        var rootView = inflater.inflate(R.layout.fragment_top_locations, container, false)
        var recyclerView:RecyclerView =  rootView.findViewById(R.id.top_locations_recycler_view)


        addLocations()


        viewManager = LinearLayoutManager(getActivity())
        viewAdapter = LocationListAdapter(locationList)

        recyclerView.layoutManager = viewManager

//        top_locations_recycler_view.apply {
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }


        return rootView


    }


    fun addLocations() {
        var loc1 = Location(1, "Location1", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )
        var loc2 = Location(2, "Location2", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )
        var loc3 = Location(3, "White house", "USA", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )
        var loc4 = Location(4, "Orheiul Vechi", "Moldova", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )
        var loc5 = Location(5, "Louvre", "Paris", "str.Lalla",
            "THis is a very beautiful location", 23, 54, 4.2F,"no image"
        )

        locationList = ArrayList<Location>(10);
        locationList.add(loc1)
        locationList.add(loc2)
        locationList.add(loc3)
        locationList.add(loc4)
        locationList.add(loc5)
    }
}