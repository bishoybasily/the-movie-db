package com.neugelb.themoviedb.di

import android.app.Application
import android.content.Context
import com.neugelb.themoviedb.ApplicationTheMovieDb
import com.neugelb.themoviedb.external.dagger.ScopeMain
import dagger.Module
import dagger.Provides

@Module
open class ModuleApplication(val applicationTheMovieDb: ApplicationTheMovieDb) {

    @ScopeMain
    @Provides
    open fun applicationTheMovieDb(): ApplicationTheMovieDb = applicationTheMovieDb

    @ScopeMain
    @Provides
    open fun application(): Application = applicationTheMovieDb

    @ScopeMain
    @Provides
    open fun context(): Context = applicationTheMovieDb


}