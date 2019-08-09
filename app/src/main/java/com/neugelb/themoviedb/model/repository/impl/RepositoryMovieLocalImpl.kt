package com.neugelb.themoviedb.model.repository.impl

import com.neugelb.themoviedb.model.data.MovieDAO
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import io.reactivex.Single

open class RepositoryMovieLocalImpl(private val movieDAO: MovieDAO) : RepositoryMovie {

    override fun find(id: String?): Single<Movie> {
        return movieDAO.findOne(id!!)
    }

    override fun save(movie: Movie): Single<Movie> {
        return Single.fromCallable { movieDAO.save(movie); movie }
    }

    override fun delete(movie: Movie): Single<Movie> {
        return Single.fromCallable { movieDAO.delete(movie); movie }
    }

}
