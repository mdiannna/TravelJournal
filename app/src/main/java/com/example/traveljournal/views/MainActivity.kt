package com.example.traveljournal.views
//https://medium.com/@er.ankitbisht/mvvm-model-view-viewmodel-kotlin-google-jetpack-f02ec7754854
//https://github.com/emedinaa/kotlin-mvvm/tree/master/KotlinMVVM
//https://dzone.com/articles/introduction-to-android-app-development-with-kotli-5
//https://www.raywenderlich.com/636803-mvvm-and-databinding-android-design-patterns#toc-anchor-006
//https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1
//https://findnerd.com/list/view/How-to-use-MVVM-Architecture-with-rxjava-and-retrofit-in-Kotlin-/38353/
//https://github.com/mayowa-egbewunmi/LocationUpdateWithLiveData/tree/master/app


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.traveljournal.R
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