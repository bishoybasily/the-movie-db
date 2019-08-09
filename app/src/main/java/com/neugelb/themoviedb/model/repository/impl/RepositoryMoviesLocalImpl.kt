package com.neugelb.themoviedb.model.repository.impl

import com.neugelb.themoviedb.model.data.MovieDAO
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import io.reactivex.Single

class RepositoryMoviesLocalImpl(private val movieDAO: MovieDAO) : RepositoryMovies {

    override fun saved(): Single<List<Movie>> {
        return movieDAO.findAll()
    }

}
