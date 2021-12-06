package com.neugelb.themoviedb.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.bishoybasily.recyclerview.EndlessRecyclerViewScrollListener
import com.gmail.bishoybasily.recyclerview.SpacingItemDecoration
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.external.dagger.LayoutManager
import com.neugelb.themoviedb.external.dagger.Orientation
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.view.activity.ActivityHome
import com.neugelb.themoviedb.view.adapter.AdapterMoviesCompat
import com.neugelb.themoviedb.view.model.ViewModelFactory
import com.neugelb.themoviedb.view.model.ViewModelMovies
import kotlinx.android.synthetic.main.fragment_movies.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FragmentMoviesSearch : FragmentBase() {

    companion object {

        fun newInstance(): FragmentMoviesSearch {
            return FragmentMoviesSearch()
        }

    }

    @field:[Inject]
    lateinit var adapterMovies: AdapterMoviesCompat

    @field:[Inject LayoutManager(orientation = Orientation.VERTICAL)]
    lateinit var linearLayoutManager: LinearLayoutManager

    @field:[Inject LayoutManager(orientation = Orientation.VERTICAL)]
    lateinit var spacingItemDecoration: SpacingItemDecoration

    @field:[Inject]
    lateinit var defaultItemAnimator: DefaultItemAnimator

    @field:[Inject]
    lateinit var factory: ViewModelFactory<ViewModelMovies>
    private val viewModel: ViewModelMovies by viewModels { factory }

    private val loader = Movie.Loader(javaClass.name)

    override fun getLayoutResourceId() = R.layout.fragment_movies

    override fun create(savedInstanceState: Bundle?) {

        ComponentMain.get().inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.firstSearchObservable.observe(viewLifecycleOwner) {
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
        }
        viewModel.nextSearchObservable.observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                Response.Status.LOADING -> {

                    if (!adapterMovies.items.contains(loader)) adapterMovies.append(loader)

                }
                Response.Status.SUCCESS -> {

                    if (adapterMovies.items.contains(loader)) adapterMovies.remove(loader)

                    adapterMovies.append(it.data!!)

                }
                Response.Status.ERROR -> {

                    if (adapterMovies.items.contains(loader)) adapterMovies.remove(loader)

                }
            }
        }

        spacingItemDecoration.apply {
            skipLookup = object : SpacingItemDecoration.SkipLookup {
                override fun shouldSkip(position: Int): Boolean {
                    if (position >= 0) return adapterMovies.get(position) is Movie.Loader
                    return false
                }
            }
        }

        recyclerView.apply {
            adapter = adapterMovies
            itemAnimator = defaultItemAnimator
            layoutManager = linearLayoutManager
            addItemDecoration(spacingItemDecoration)
            addOnScrollListener(object : EndlessRecyclerViewScrollListener() {

                override fun onLoadMore() {
                    viewModel.nextSearchMovies()
                }

            })
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.firstSearchMovies()
        }

        compositeDisposable.add(

            (activity as ActivityHome).searchObservable().debounce(500, TimeUnit.MILLISECONDS)
                .subscribe { viewModel.firstSearchMovies(it) }

        )

    }

    override fun destroy() {

    }

}
