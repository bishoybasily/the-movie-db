package com.neugelb.themoviedb.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.neugelb.themoviedb.Constants
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.view.model.ViewModelFactory
import com.neugelb.themoviedb.view.model.ViewModelMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_activity_movie.*
import javax.inject.Inject

class ActivityMovie : ActivityBase() {

    @field:[Inject]
    lateinit var picasso: Picasso

    @field:[Inject]
    lateinit var factory: ViewModelFactory<ViewModelMovie>
    private val viewModel: ViewModelMovie by viewModels { factory }

    private val movie by lazy { intent?.extras?.getSerializable(Constants.Extra.MOVIE) as Movie }

    companion object {

        fun start(activity: ComponentActivity, movie: Movie, transitionView: View) {
            val intent = Intent(activity, ActivityMovie::class.java)
            intent.putExtra(Constants.Extra.MOVIE, movie)
            val pairLogo = Pair.create(transitionView, transitionView.transitionName)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairLogo)
            val bundle = options.toBundle()
            activity.startActivity(intent, bundle)
        }

    }

    override fun getLayoutResourceId() = R.layout.activity_movie

    override fun create(savedInstanceState: Bundle?) {

        ComponentMain.get().inject(this)

        setSupportActionBar(toolbar)

        picasso.load(Constants.API.BASE_MEDIA_URL + movie.posterUrl)
            .into(imageViewPoster)

        title = movie.title
        textViewDescription.text = movie.overview

        changeButtonIcon(movie)

        fab.setOnClickListener { viewModel.toggleSave(movie) }

        viewModel.toggleObservable.observe(this) {
            when (it.getStatus()) {
                Response.Status.SUCCESS -> {
                    it.data?.let { changeButtonIcon(it) }
                }
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun changeButtonIcon(movie: Movie) {
        fab.setImageResource(if (movie.saved) R.drawable.ic_favorite_white_24dp else R.drawable.ic_favorite_border_white_24dp)
    }

}
