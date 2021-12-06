package com.neugelb.themoviedb.model.repository

import com.neugelb.themoviedb.model.entity.Movie
import io.reactivex.Single

interface RepositoryMovie {

    fun find(id: String): Single<Movie> {
        return Single.error(UnsupportedOperationException())
    }

    fun save(movie: Movie): Single<Movie> {
        return Single.error(UnsupportedOperationException())
    }

    fun delete(movie: Movie): Single<Movie> {
        return Single.error(UnsupportedOperationException())
    }

}