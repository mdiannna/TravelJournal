package com.example.traveljournal.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.traveljournal.R

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveljournal.views.Location
import com.example.traveljournal.adapters.LocationListAdapter
import com.example.traveljournal.viewmodels.TopLocationsViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class TopLocationsFragment: Fragment() {
//    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    // private lateinit var locationList: ArrayList<Location>;
//    private lateinit var viewModel: TopLocationsViewModel;
//    private var rootView: View? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        var rootView = inflater.inflate(R.layout.fragment_top_locations, container, false)
        var recyclerView: RecyclerView = rootView.findViewById(R.id.top_locations_recycler_view)

        val viewModel = ViewModelProviders.of(this).get(TopLocationsViewModel::class.java)

        viewManager = LinearLayoutManager(getActivity())
        viewAdapter =
            LocationListAdapter(viewModel.getLocationList())

        recyclerView.layoutManager = viewManager

//        top_locations_recycler_view.apply {
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }

        return rootView

    }
}