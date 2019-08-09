package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.model.data.MovieDAO
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import org.mockito.Mockito
import retrofit2.Retrofit

class ModuleRepositoryTest : ModuleRepository() {

    override fun repositoryMovieLocal(movieDAO: MovieDAO): RepositoryMovie {
        return Mockito.mock(RepositoryMovie::class.java)
    }

    override fun repositoryMoviesLocal(movieDAO: MovieDAO): RepositoryMovies {
        return Mockito.mock(RepositoryMovies::class.java)
    }

    override fun repositoryMoviesNetwork(retrofit: Retrofit): RepositoryMovies {
        return Mockito.mock(RepositoryMovies::class.java)
    }

}
