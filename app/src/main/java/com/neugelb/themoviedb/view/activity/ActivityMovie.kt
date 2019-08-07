package com.neugelb.themoviedb.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.google.android.material.snackbar.Snackbar
import com.neugelb.themoviedb.Constants
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.model.entity.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_activity_movie.*
import javax.inject.Inject

class ActivityMovie : ActivityBase() {

    @field:[Inject]
    lateinit var picasso: Picasso

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

        picasso.load(Constants.API.BASE_MEDIA_URL + movie.posterUrl).into(imageViewPoster)

        title = movie.title
        textViewDescription.text = movie.overview

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

}
