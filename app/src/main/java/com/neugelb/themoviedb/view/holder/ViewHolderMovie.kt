package com.neugelb.themoviedb.view.holder

import android.view.View
import androidx.activity.ComponentActivity
import com.neugelb.themoviedb.Constants
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.view.activity.ActivityMovie
import com.neugelb.themoviedb.view.adapter.AdapterMovies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import javax.inject.Inject

class ViewHolderMovie(
    adapterMovies: AdapterMovies,
    view: View
) : AbstractViewHolderMovie(adapterMovies, view) {

    @field:[Inject]
    lateinit var picasso: Picasso

    override fun attached(i: Movie) {

        ComponentMain.get().inject(this)

        picasso.load(Constants.API.BASE_MEDIA_URL + i.posterUrl).into(view.imageView)

        view.textViewDescription.setTextNew("${i.title}\n\nReleased on ${i.date},\n${if (i.isAdult) "+18" else "family movie"}")

        view.setOnClickListener { ActivityMovie.start(view.context as ComponentActivity, i, view.imageView) }

    }

    override fun detached(i: Movie) {

    }

}
