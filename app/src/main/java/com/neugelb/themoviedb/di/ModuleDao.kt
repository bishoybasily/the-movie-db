package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.model.data.DatabaseTheMovieDb
import com.neugelb.themoviedb.model.data.MovieDao
import dagger.Module
import dagger.Provides

@Module
class ModuleDao {

    @ScopeMain
    @Provides
    fun movieDao(db: DatabaseTheMovieDb): MovieDao {
        return db.movieDAO()
    }

}