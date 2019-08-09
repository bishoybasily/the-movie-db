package com.neugelb.themoviedb.model.repository.impl

import com.neugelb.themoviedb.model.data.BackendTheMovieDb
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import retrofit2.Retrofit

class RepositoryMoviesNetworkImpl(private val retrofit: Retrofit) : RepositoryMovies {

    override fun popular(page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .popular(page)

    override fun upcoming(page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .upcoming(page)

    override fun nowPlaying(page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .nowPlaying(page)

    override fun topRated(page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .topRated(page)

    override fun search(query: String, page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .search(query, page)

}
