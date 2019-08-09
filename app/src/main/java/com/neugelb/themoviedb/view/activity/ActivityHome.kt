package com.neugelb.themoviedb.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.helper.ReactiveHelper
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.view.fragment.FragmentMovies
import com.neugelb.themoviedb.view.fragment.FragmentMoviesSearch
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class ActivityHome : ActivityBase(), BottomNavigationView.OnNavigationItemSelectedListener {

    @field:[Inject]
    lateinit var reactiveHelper: ReactiveHelper

    lateinit var searchView: SearchView

    companion object {

        fun start(activity: ComponentActivity) {
            Intent(activity, ActivityHome::class.java)
                .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) }
                .also(activity::startActivity)
        }

    }

    override fun getLayoutResourceId() = R.layout.activity_home


    override fun create(savedInstanceState: Bundle?) {

        ComponentMain.get().inject(this)

        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_popular -> {
                showFragment(FragmentMovies.newInstance(Source.POPULAR))
                return true
            }
            R.id.navigation_upcoming -> {
                showFragment(FragmentMovies.newInstance(Source.UPCOMING))
                return true
            }
            R.id.navigation_top_rated -> {
                showFragment(FragmentMovies.newInstance(Source.TOP_RATED))
                return true
            }
            R.id.navigation_now_playing -> {
                showFragment(FragmentMovies.newInstance(Source.NOW_PLAYING))
                return true
            }
        }

        return false
    }

    fun searchObservable(): Observable<String> {
        return reactiveHelper.searchView(searchView)
    }

    private fun showFragment(fragmentMoviesSearch1: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder, fragmentMoviesSearch1)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_home, menu)

        searchView = (menu.findItem(R.id.action_search).actionView as SearchView).apply {
            setOnSearchClickListener {
                showFragment(FragmentMoviesSearch.newInstance())
            }
        }

        return true
    }

    override fun optionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
