package com.neugelb.themoviedb.view.holder

import android.view.View
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.view.adapter.AdapterMoviesCompat
import kotlinx.android.synthetic.main.item_movie_compat.view.*

class ViewHolderMovieCompat(
    adapterMoviesCompat: AdapterMoviesCompat,
    view: View
) : AbstractViewHolderMovie(adapterMoviesCompat, view) {

    override fun attached(i: Movie) {

        ComponentMain.get().inject(this)

        view.textViewTitle.text = i.title
    }

    override fun detached(i: Movie) {

    }

}
