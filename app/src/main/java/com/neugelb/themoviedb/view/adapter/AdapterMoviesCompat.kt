package com.neugelb.themoviedb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.view.holder.AbstractViewHolderMovie
import com.neugelb.themoviedb.view.holder.ViewHolderMovieCompat
import javax.inject.Inject

class AdapterMoviesCompat
@Inject
constructor() : AdapterMovies() {

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolderMovie {
        return ViewHolderMovieCompat(
            this,
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_compat, parent, false)
        )
    }

}
