package com.example.traveljournal.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.traveljournal.R

class AboutFragment: Fragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_about, null)
        }

        return rootView
    }
}