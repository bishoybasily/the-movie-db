package com.neugelb.themoviedb.view.holder

import android.view.View
import com.neugelb.themoviedb.Constants
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.external.picasso.RoundTransform
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.view.adapter.AdapterMoviesCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_compat.view.*
import javax.inject.Inject

class ViewHolderMovieCompat(
    adapterMoviesCompat: AdapterMoviesCompat,
    view: View
) : AbstractViewHolderMovie(adapterMoviesCompat, view) {

    @field:[Inject]
    lateinit var picasso: Picasso

    override fun attached(i: Movie) {

        ComponentMain.get().inject(this)

        picasso.load(Constants.API.BASE_MEDIA_URL + i.posterUrl).transform(RoundTransform()).into(view.imageView)
        view.textViewTitle.text = i.title
    }

    override fun detached(i: Movie) {

    }

}