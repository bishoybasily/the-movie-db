package com.neugelb.themoviedb.view.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.helper.ReactiveHelper
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.view.fragment.FragmentMovies
import kotlinx.android.synthetic.main.activity_home.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ActivityHome : ActivityBase(), BottomNavigationView.OnNavigationItemSelectedListener {

    @field:[Inject]
    lateinit var reactiveHelper: ReactiveHelper

    val fragmentMoviesPopular by lazy { FragmentMovies.newInstance(Source.POPULAR) }
    val fragmentMoviesUpcoming by lazy { FragmentMovies.newInstance(Source.UPCOMING) }
    val fragmentMoviesTopRated by lazy { FragmentMovies.newInstance(Source.TOP_RATED) }
    val fragmentMoviesNowPlaying by lazy { FragmentMovies.newInstance(Source.NOW_PLAYING) }

    override fun getLayoutResourceId() = R.layout.activity_home

    override fun create(savedInstanceState: Bundle?) {

        ComponentMain.get().inject(this)

        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_popular -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, fragmentMoviesPopular)
                    .commit()
                return true
            }
            R.id.navigation_upcoming -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, fragmentMoviesUpcoming)
                    .commit()
                return true
            }
            R.id.navigation_top_rated -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, fragmentMoviesTopRated)
                    .commit()
                return true
            }
            R.id.navigation_now_playing -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, fragmentMoviesNowPlaying)
                    .commit()
                return true
            }
        }

        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_home, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.suggestionsAdapter

        componeDisposable.add(

            reactiveHelper.searchView(searchView)
                .debounce(1, TimeUnit.SECONDS)
                .subscribe { Log.w("##", it) }

        )

        return true
    }

    override fun optionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
