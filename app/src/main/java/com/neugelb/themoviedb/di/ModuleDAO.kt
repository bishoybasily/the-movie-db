package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.model.data.DatabaseTheMovieDb
import com.neugelb.themoviedb.model.data.MovieDAO
import dagger.Module
import dagger.Provides

@Module
class ModuleDAO {

    @ScopeMain
    @Provides
    fun movieDao(db: DatabaseTheMovieDb): MovieDAO {
        return db.movieDAO()
    }

}