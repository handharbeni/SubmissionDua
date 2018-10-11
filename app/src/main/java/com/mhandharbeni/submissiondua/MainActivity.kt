package com.mhandharbeni.submissiondua

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.mhandharbeni.submissiondua.adapter.PagerAdapter
import com.mhandharbeni.submissiondua.ui.MainActivityUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity(){
    private lateinit var tabMain: TabLayout
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI<MainActivity>().setContentView(this)
        val fragmentAdapter = PagerAdapter(supportFragmentManager)

        viewPager = find(R.id.viewPagerMain)
        tabMain = find(R.id.tabMain)

        viewPager.adapter = fragmentAdapter
        tabMain.setupWithViewPager(viewPager)
    }
}
