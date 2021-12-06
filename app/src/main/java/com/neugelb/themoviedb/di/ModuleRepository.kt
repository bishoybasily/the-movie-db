package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.external.dagger.Network
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.model.data.MovieDao
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import com.neugelb.themoviedb.model.repository.impl.RepositoryMovieLocalImpl
import com.neugelb.themoviedb.model.repository.impl.RepositoryMoviesLocalImpl
import com.neugelb.themoviedb.model.repository.impl.RepositoryMoviesNetworkImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
open class ModuleRepository {

    @Local
    @ScopeMain
    @Provides
    open fun repositoryMoviesLocal(movieDAO: MovieDao): RepositoryMovies {
        return RepositoryMoviesLocalImpl(movieDAO)
    }

    @Network
    @ScopeMain
    @Provides
    open fun repositoryMoviesNetwork(retrofit: Retrofit): RepositoryMovies {
        return RepositoryMoviesNetworkImpl(retrofit)
    }


    @Local
    @ScopeMain
    @Provides
    open fun repositoryMovieLocal(movieDAO: MovieDao): RepositoryMovie {
        return RepositoryMovieLocalImpl(movieDAO)
    }

}