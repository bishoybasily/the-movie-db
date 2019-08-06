package com.neugelb.themoviedb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.bishoybasily.recyclerview.EndlessRecyclerViewAdapter
import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.view.holder.AbstractViewHolderMovie
import com.neugelb.themoviedb.view.holder.ViewHolderMovie
import com.neugelb.themoviedb.view.holder.ViewHolderMovieLoader
import javax.inject.Inject

class AdapterMovies
@Inject
constructor() : EndlessRecyclerViewAdapter<Movie, AbstractViewHolderMovie>() {

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolderMovie {
        return ViewHolderMovie(
            this,
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onCreateItemLoaderViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolderMovie {
        return ViewHolderMovieLoader(
            this,
            LayoutInflater.from(parent.context).inflate(R.layout.item_loader, parent, false)
        )
    }

}
