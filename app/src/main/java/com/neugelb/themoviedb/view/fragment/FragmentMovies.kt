package com.neugelb.themoviedb.view.fragment

import android.os.Bundle
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.view.activity.viewModel
import com.neugelb.themoviedb.view.model.ViewModelMovies
import javax.inject.Inject

class FragmentMovies : FragmentBase() {

    companion object {
        fun newInstance() = FragmentMovies()
    }

    @Inject
    lateinit var logHelper: LogHelper

    @Inject
    lateinit var factory: ViewModelMovies.Factory
    private val viewModel by lazy { viewModel(ViewModelMovies::class.java, factory) }

    override fun getLayoutResourceId() = R.layout.fragment_movies

    override fun create(savedInstanceState: Bundle?) {
        ComponentMain.get().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        logHelper.info("##", "${::factory.isInitialized}")

    }

    override fun destroy() {

    }

}
