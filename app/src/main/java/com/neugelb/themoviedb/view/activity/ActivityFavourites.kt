package com.neugelb.themoviedb.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.gmail.bishoybasily.recyclerview.SpacingItemDecoration
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.external.dagger.Count
import com.neugelb.themoviedb.external.dagger.LayoutManager
import com.neugelb.themoviedb.external.dagger.Orientation
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.view.adapter.AdapterMovies
import com.neugelb.themoviedb.view.model.ViewModelFavourites
import kotlinx.android.synthetic.main.activity_favourites.*
import kotlinx.android.synthetic.main.content_activity_favourites.*
import javax.inject.Inject

class ActivityFavourites : ActivityBase() {

    @field:[Inject]
    lateinit var adapterMovies: AdapterMovies
    @field:[Inject LayoutManager(Count._2, Orientation.VERTICAL)]
    lateinit var gridLayoutManager: GridLayoutManager
    @field:[Inject LayoutManager(Count._2)]
    lateinit var spacingItemDecoration: SpacingItemDecoration
    @field:[Inject]
    lateinit var defaultItemAnimator: DefaultItemAnimator
    @field:[Inject]
    lateinit var factory: ViewModelFavourites.Factory
    private val viewModel by lazy { viewModel(ViewModelFavourites::class.java, factory) }

    companion object {

        fun start(activity: ComponentActivity) {
            Intent(activity, ActivityFavourites::class.java).also(activity::startActivity)
        }

    }

    override fun getLayoutResourceId() = R.layout.activity_favourites

    override fun create(savedInstanceState: Bundle?) {

        ComponentMain.get().inject(this)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.allObservable.observe(this, Observer {
            when (it.getStatus()) {
                Response.Status.LOADING -> {
                    swipeRefreshLayout.isRefreshing = true
                }
                Response.Status.SUCCESS -> {
                    swipeRefreshLayout.isRefreshing = false
                    adapterMovies.show(it.data!!)
                }
                Response.Status.ERROR -> {
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        })

        recyclerView.apply {
            adapter = adapterMovies
            itemAnimator = defaultItemAnimator
            layoutManager = gridLayoutManager
            addItemDecoration(spacingItemDecoration)
        }

        swipeRefreshLayout.setOnRefreshListener { viewModel.allMovies() }

    }

    override fun onResume() {
        super.onResume()

        viewModel.allMovies()

    }

}
