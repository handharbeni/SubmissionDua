package com.mhandharbeni.submissiondua.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mhandharbeni.submissiondua.fragment.FragmentNext
import com.mhandharbeni.submissiondua.fragment.FragmentPrevious

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0       -> FragmentPrevious()
            else    -> FragmentNext()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0       -> "Previous Fixtures"
            else    -> "Next Fixtures"
        }
    }
}