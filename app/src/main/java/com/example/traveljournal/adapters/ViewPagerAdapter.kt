package com.example.traveljournal.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.traveljournal.fragments.AboutFragment
import com.example.traveljournal.fragments.MapFragment
import com.example.traveljournal.fragments.TopLocationsFragment
import com.example.traveljournal.fragments.JournalsFragment


class ViewPagerAdapter(fragmentManager: FragmentManager):
//    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    FragmentStatePagerAdapter(fragmentManager) {

    private val tabTitles: Array<String> = arrayOf(
        "Journals",
        "About",
        "Map",
        "Top locations"
    )

    private val pagerFragments: Array<Fragment> = arrayOf(
        JournalsFragment(),
        AboutFragment(),
        MapFragment(),
        TopLocationsFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pagerFragments[position]
    }

    override fun getCount(): Int {
        return pagerFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}