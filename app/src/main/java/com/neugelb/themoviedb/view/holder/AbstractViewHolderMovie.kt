package com.neugelb.themoviedb.view.holder

import android.view.View
import com.gmail.bishoybasily.recyclerview.RecyclerViewViewHolder
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.view.adapter.EndlessAdapterMovies

abstract class AbstractViewHolderMovie(
    adapter: EndlessAdapterMovies,
    view: View
) : RecyclerViewViewHolder<Movie>(adapter, view)
