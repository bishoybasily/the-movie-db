package com.neugelb.themoviedb.model.data

import com.neugelb.themoviedb.Constants
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BackendTheMovieDb {

    interface EndpointMovie {

        @GET(Constants.API.Endpoints.MOVIE_CREDITS)
        fun credits(
            @Query("page") page: Int
        ): Single<Page<Movie>>

        @GET(Constants.API.Endpoints.MOVIE_IMAGES)
        fun images(
            @Query("page") page: Int
        ): Single<Page<Movie>>

        @GET(Constants.API.Endpoints.MOVIE_VIDEOS)
        fun videos(
            @Query("page") page: Int
        ): Single<Page<Movie>>

    }

    interface MoviesService {

        @GET(Constants.API.Endpoints.MOVIES_POPULAR)
        fun popular(
            @Query("page") page: Int
        ): Single<Page<Movie>>

        @GET(Constants.API.Endpoints.MOVIES_UPCOMING)
        fun upcoming(
            @Query("page") page: Int
        ): Single<Page<Movie>>

        @GET(Constants.API.Endpoints.MOVIES_NOW_PLAYING)
        fun nowPlaying(
            @Query("page") page: Int
        ): Single<Page<Movie>>

        @GET(Constants.API.Endpoints.MOVIES_TOP_RATED)
        fun topRated(
            @Query("page") page: Int
        ): Single<Page<Movie>>

        @GET(Constants.API.Endpoints.SEARCH_MOVIES)
        fun search(
            @Query("query") query: String,
            @Query("page") page: Int
        ): Single<Page<Movie>>

    }

}
