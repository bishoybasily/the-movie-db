package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.external.dagger.Network
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.model.data.MovieDAO
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import com.neugelb.themoviedb.model.repository.impl.RepositoryMovieLocalImpl
import com.neugelb.themoviedb.model.repository.impl.RepositoryMoviesLocalImpl
import com.neugelb.themoviedb.model.repository.impl.RepositoryMoviesNetworkImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ModuleRepository {

    @Local
    @ScopeMain
    @Provides
    fun repositoryMoviesLocal(movieDAO: MovieDAO): RepositoryMovies {
        return RepositoryMoviesLocalImpl(movieDAO)
    }

    @Network
    @ScopeMain
    @Provides
    fun repositoryMoviesNetwork(retrofit: Retrofit): RepositoryMovies {
        return RepositoryMoviesNetworkImpl(retrofit)
    }


    @Local
    @ScopeMain
    @Provides
    fun repositoryMovieLocal(movieDAO: MovieDAO): RepositoryMovie {
        return RepositoryMovieLocalImpl(movieDAO)
    }

}