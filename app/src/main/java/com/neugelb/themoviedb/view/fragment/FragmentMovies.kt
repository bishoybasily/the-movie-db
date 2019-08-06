package com.neugelb.themoviedb.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.neugelb.themoviedb.Constants
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.view.activity.viewModel
import com.neugelb.themoviedb.view.model.ViewModelMovies
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

    @Inject
    lateinit var factory: ViewModelMovies.Factory
    private val viewModel by lazy { viewModel(ViewModelMovies::class.java, factory) }

    private val source by lazy { arguments?.getSerializable(Constants.Extra.SOURCE) as Source }

    override fun getLayoutResourceId() = R.layout.fragment_movies

    override fun create(savedInstanceState: Bundle?) {
        ComponentMain.get().inject(this)

        viewModel.firstObservable.observe(this, Observer {
            Log.w("##", Gson().toJson(it))
        })
        viewModel.firstMovies(source)

    }

    override fun destroy() {

    }

}
