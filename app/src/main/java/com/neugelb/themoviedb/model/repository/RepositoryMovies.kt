package com.neugelb.themoviedb.model.repository

import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import io.reactivex.Single

interface RepositoryMovies {

    fun popular(page: Int): Single<Page<Movie>> {
        return Single.error(UnsupportedOperationException("Unimplemented operation"))
    }

    fun upcoming(page: Int): Single<Page<Movie>> {
        return Single.error(UnsupportedOperationException("Unimplemented operation"))
    }

    fun nowPlaying(page: Int): Single<Page<Movie>> {
        return Single.error(UnsupportedOperationException("Unimplemented operation"))
    }

    fun topRated(page: Int): Single<Page<Movie>> {
        return Single.error(UnsupportedOperationException("Unimplemented operation"))
    }

    fun saved(page: Int): Single<Page<Movie>> {
        return Single.error(UnsupportedOperationException("Unimplemented operation"))
    }

    fun search(query: String, page: Int): Single<Page<Movie>> {
        return Single.error(UnsupportedOperationException("Unimplemented operation"))
    }

}