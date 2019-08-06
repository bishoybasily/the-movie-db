package com.neugelb.themoviedb.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.view.fragment.FragmentMovies
import kotlinx.android.synthetic.main.activity_home.*

class ActivityHome : ActivityBase(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun getLayoutResourceId() = R.layout.activity_home

    override fun create(savedInstanceState: Bundle?) {
        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_popular -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, FragmentMovies.newInstance(Source.POPULAR))
                    .commit()
                return true
            }
            R.id.navigation_upcoming -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, FragmentMovies.newInstance(Source.UPCOMING))
                    .commit()
                return true
            }
            R.id.navigation_top_rated -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, FragmentMovies.newInstance(Source.TOP_RATED))
                    .commit()
                return true
            }
            R.id.navigation_now_playing -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, FragmentMovies.newInstance(Source.NOW_PLAYING))
                    .commit()
                return true
            }
        }

        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_home, menu)
        return true
    }

    override fun optionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
