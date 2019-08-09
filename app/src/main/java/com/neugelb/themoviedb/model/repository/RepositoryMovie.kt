package com.neugelb.themoviedb.model.repository

import com.neugelb.themoviedb.model.entity.Movie
import io.reactivex.Single

interface RepositoryMovie {

    fun isSaved(id: String): Single<Boolean> {
        return Single.error(UnsupportedOperationException())
    }

    fun save(movie: Movie): Single<Movie> {
        return Single.error(UnsupportedOperationException())
    }

    fun remove(movie: Movie): Single<Void> {
        return Single.error(UnsupportedOperationException())
    }

}