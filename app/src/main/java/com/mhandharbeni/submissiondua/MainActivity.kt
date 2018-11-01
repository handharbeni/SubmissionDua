package com.mhandharbeni.submissiondua

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.mhandharbeni.submissiondua.fragment.*

class MainActivity : AppCompatActivity(){
    private lateinit var tabMain: TabLayout
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        MainActivityUI<MainActivity>().setContentView(this)
//        val fragmentAdapter = PagerAdapter(supportFragmentManager)

//        viewPager = find(R.id.viewPagerMain)
//        tabMain = find(R.id.tabMain)
//
//        viewPager.adapter = fragmentAdapter
//        tabMain.setupWithViewPager(viewPager)

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = MatchesFragment()
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.frameLayout, fragment, fragment.javaClass.getSimpleName())
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
    }

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.navigation_matches -> {

                    val fragment = MatchesFragment()
                    addFragment(fragment)

                    return true
                }
                R.id.navigation_teams -> {
                    val fragment = FragmentTeam()
                    addFragment(fragment)
                    return true
                }
                R.id.navigation_favorite -> {
                    var fragment = FavoritesFragment()
                    addFragment(fragment)
                    return true
                }
            }
            return false
        }
    }
}
