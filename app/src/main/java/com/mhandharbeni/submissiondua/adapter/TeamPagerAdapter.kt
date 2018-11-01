package com.mhandharbeni.submissiondua.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mhandharbeni.submissiondua.fragment.FragmentPrevious
import com.mhandharbeni.submissiondua.fragment.FragmentOverview

class TeamPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0       -> FragmentOverview()
            else       -> FragmentPrevious()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0       -> "Overview"
            else       -> "Player"
        }
    }
}