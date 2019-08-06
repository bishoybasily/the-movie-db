package com.neugelb.themoviedb.view.activity

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.view.fragment.FragmentMovies
import kotlinx.android.synthetic.main.activity_home.*

class ActivityHome : ActivityBase(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun getLayoutResourceId() = R.layout.activity_home

    override fun create(savedInstanceState: Bundle?) {
        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction().add(R.id.fragmentHolder, FragmentMovies.newInstance())
                    .commit()
                return true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction().add(R.id.fragmentHolder, FragmentMovies.newInstance())
                    .commit()
                return true
            }
            R.id.navigation_notifications -> {
                supportFragmentManager.beginTransaction().add(R.id.fragmentHolder, FragmentMovies.newInstance())
                    .commit()
                return true
            }
        }

        return false
    }

}
