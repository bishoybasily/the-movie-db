package com.neugelb.themoviedb.model.repository

import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.model.data.BackendTheMovieDb
import retrofit2.Retrofit
import javax.inject.Inject

@ScopeMain
class RepositoryMovies
@Inject
constructor(private val retrofit: Retrofit) {

    fun popular(page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .popular(page)

    fun upcoming(page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .upcoming(page)

    fun nowPlaying(page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .nowPlaying(page)

    fun topRated(page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .topRated(page)

    fun latest(page: Int) = retrofit
        .create(BackendTheMovieDb.MoviesService::class.java)
        .latest(page)

}
