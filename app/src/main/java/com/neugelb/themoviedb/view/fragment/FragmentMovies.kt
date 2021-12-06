package com.neugelb.themoviedb.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.gmail.bishoybasily.recyclerview.EndlessRecyclerViewScrollListener
import com.gmail.bishoybasily.recyclerview.SpacingItemDecoration
import com.neugelb.themoviedb.Constants
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.external.dagger.Count
import com.neugelb.themoviedb.external.dagger.LayoutManager
import com.neugelb.themoviedb.external.dagger.Orientation
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.view.adapter.AdapterMovies
import com.neugelb.themoviedb.view.model.ViewModelFactory
import com.neugelb.themoviedb.view.model.ViewModelMovies
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class FragmentMovies : FragmentBase() {

    companion object {

        fun newInstance(source: Source): FragmentMovies {
            val fragment = FragmentMovies()
            val args = Bundle()
            args.putSerializable(Constants.Extra.SOURCE, source)
            fragment.arguments = args
            return fragment
        }

    }

    @field:[Inject]
    lateinit var adapterMovies: AdapterMovies

    @field:[Inject LayoutManager(Count._2, Orientation.VERTICAL)]
    lateinit var gridLayoutManager: GridLayoutManager

    @field:[Inject LayoutManager(Count._2)]
    lateinit var spacingItemDecoration: SpacingItemDecoration

    @field:[Inject]
    lateinit var defaultItemAnimator: DefaultItemAnimator

    @field:[Inject]
    lateinit var factory: ViewModelFactory<ViewModelMovies>
    private val viewModel: ViewModelMovies by viewModels { factory }

    private val loader = Movie.Loader(javaClass.name)

    private val source by lazy { arguments?.getSerializable(Constants.Extra.SOURCE) as Source }

    override fun getLayoutResourceId() = R.layout.fragment_movies

    override fun create(savedInstanceState: Bundle?) {

        ComponentMain.get().inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.firstObservable.observe(viewLifecycleOwner) {
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
        viewModel.nextObservable.observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                Response.Status.LOADING -> {

                    if (!adapterMovies.items.contains(loader))
                        adapterMovies.append(loader)

                }
                Response.Status.SUCCESS -> {

                    if (adapterMovies.items.contains(loader))
                        adapterMovies.remove(loader)

                    adapterMovies.append(it.data!!)

                }
                Response.Status.ERROR -> {

                    if (adapterMovies.items.contains(loader))
                        adapterMovies.remove(loader)

                }
            }
        }

        gridLayoutManager.apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position >= 0)
                        if (adapterMovies.get(position) is Movie.Loader)
                            return 2
                    return 1
                }

            }
        }

        spacingItemDecoration.apply {
            skipLookup = object : SpacingItemDecoration.SkipLookup {
                override fun shouldSkip(position: Int): Boolean {
                    if (position >= 0)
                        return adapterMovies.get(position) is Movie.Loader
                    return false
                }
            }
        }

        recyclerView.apply {
            adapter = adapterMovies
            itemAnimator = defaultItemAnimator
            layoutManager = gridLayoutManager
            addItemDecoration(spacingItemDecoration)
            addOnScrollListener(object : EndlessRecyclerViewScrollListener(8) {

                override fun onLoadMore() {
                    viewModel.nextMovies()
                }

            })
        }

        swipeRefreshLayout.setOnRefreshListener { viewModel.firstMovies(source) }

        viewModel.firstMovies(source)

    }

    override fun destroy() {

    }

}
