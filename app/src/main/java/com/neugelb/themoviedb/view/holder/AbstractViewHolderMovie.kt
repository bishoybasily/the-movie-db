package com.neugelb.themoviedb.view.holder

import android.view.View
import com.gmail.bishoybasily.recyclerview.RecyclerViewViewHolder
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.view.adapter.AdapterMovies

abstract class AbstractViewHolderMovie(
    adapter: AdapterMovies,
    view: View
) : RecyclerViewViewHolder<Movie>(adapter, view)
