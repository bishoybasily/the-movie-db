package com.neugelb.themoviedb.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.gmail.bishoybasily.recyclerview.EndlessRecyclerViewScrollListener
import com.gmail.bishoybasily.recyclerview.GridSpacingItemDecoration
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
import com.neugelb.themoviedb.view.activity.ActivityMovie
import com.neugelb.themoviedb.view.activity.viewModel
import com.neugelb.themoviedb.view.adapter.EndlessAdapterMovies
import com.neugelb.themoviedb.view.model.ViewModelMovies
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.item_movie.view.*
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
    lateinit var endlessAdapterMovies: EndlessAdapterMovies
    @field:[Inject LayoutManager(count = Count._2, orientation = Orientation.PORTRAIT)]
    lateinit var gridLayoutManager: GridLayoutManager
    @field:[Inject LayoutManager(count = Count._2)]
    lateinit var itemDecoration: GridSpacingItemDecoration
    @field:[Inject]
    lateinit var defaultItemAnimator: DefaultItemAnimator
    @field:[Inject]
    lateinit var factory: ViewModelMovies.Factory
    private val viewModel by lazy { viewModel(ViewModelMovies::class.java, factory) }

    private val source by lazy { arguments?.getSerializable(Constants.Extra.SOURCE) as Source }

    private val loader = Movie.Loader(javaClass.name)

    override fun getLayoutResourceId() = R.layout.fragment_movies

    override fun create(savedInstanceState: Bundle?) {

        ComponentMain.get().inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.firstObservable.observe(this, Observer {
            when (it.getStatus()) {
                Response.Status.LOADING -> {
                    swipeRefreshLayout.isRefreshing = true
                }
                Response.Status.SUCCESS -> {
                    swipeRefreshLayout.isRefreshing = false
                    endlessAdapterMovies.show(it.data!!)
                }
                Response.Status.ERROR -> {
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        })
        viewModel.nextObservable.observe(this, Observer {
            when (it.getStatus()) {
                Response.Status.LOADING -> {
                    endlessAdapterMovies.append(loader)
                    recyclerView.post { recyclerView.scrollToPosition(endlessAdapterMovies.itemCount - 1) }

                }
                Response.Status.SUCCESS -> {
                    endlessAdapterMovies.append(it.data!!)
                    endlessAdapterMovies.remove(loader)
                }
                Response.Status.ERROR -> {
                    endlessAdapterMovies.remove(loader)
                }
            }
        })

        endlessAdapterMovies.onClick { movie, view -> activity?.let { ActivityMovie.start(it, movie, view.imageView) } }

        gridLayoutManager.apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position >= 0)
                        if (endlessAdapterMovies.get(position) is Movie.Loader)
                            return 2
                    return 1
                }

            }
        }

        itemDecoration.apply {
            skipLookup = object : SpacingItemDecoration.SkipLookup {
                override fun shouldSkip(position: Int): Boolean {
                    if (position >= 0)
                        return endlessAdapterMovies.get(position) is Movie.Loader
                    return false
                }
            }
        }

        recyclerView.apply {
            adapter = endlessAdapterMovies
            itemAnimator = defaultItemAnimator
            layoutManager = gridLayoutManager
            addItemDecoration(itemDecoration)
            addOnScrollListener(object : EndlessRecyclerViewScrollListener() {

                override fun onLoadMore() {
                    viewModel.nextMovies(source)
                }

            })
        }

        swipeRefreshLayout.setOnRefreshListener { viewModel.firstMovies(source) }

        viewModel.firstMovies(source)

    }

    override fun destroy() {

    }

}
