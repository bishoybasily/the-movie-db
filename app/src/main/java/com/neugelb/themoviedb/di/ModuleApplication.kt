package com.neugelb.themoviedb.di

import android.app.Application
import android.content.Context
import com.neugelb.themoviedb.TheMovieDbApplication
import com.neugelb.themoviedb.external.dagger.ScopeMain
import dagger.Module
import dagger.Provides

@Module
open class ModuleApplication(val theMovieDbApplication: TheMovieDbApplication) {

    @ScopeMain
    @Provides
    open fun applicationTheMovieDb(): TheMovieDbApplication = theMovieDbApplication

    @ScopeMain
    @Provides
    open fun application(): Application = theMovieDbApplication

    @ScopeMain
    @Provides
    open fun context(): Context = theMovieDbApplication


}