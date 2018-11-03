package com.mhandharbeni.submissiondua.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mhandharbeni.submissiondua.fragment.FragmentFavourite
import com.mhandharbeni.submissiondua.fragment.FragmentTeamFavourite

class FavoritePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0       -> FragmentFavourite()
            else       -> FragmentTeamFavourite()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0       -> "Match Favorite"
            else       -> "Team Favorite"
        }
    }
}