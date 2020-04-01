package com.example.traveljournal


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.traveljournal.adapters.ViewPagerAdapter

//import com.sample.app.extensions.setStatusBarColor

import kotlinx.android.synthetic.main.activity_tab_layout.*;

class MainActivity : AppCompatActivity() {

    private val pagerAdapter: ViewPagerAdapter = ViewPagerAdapter(this.supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        setSupportActionBar(findViewById(R.id.toolbar))

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}