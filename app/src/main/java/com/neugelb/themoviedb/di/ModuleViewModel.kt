package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.TheMovieDbApplication
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.usecase.UsecaseFetchMovies
import com.neugelb.themoviedb.model.usecase.UsecaseSavedMovies
import com.neugelb.themoviedb.model.usecase.UsecaseSearchMovies
import com.neugelb.themoviedb.model.usecase.UsecaseToggleSaveMovie
import com.neugelb.themoviedb.view.model.ViewModelFavourites
import com.neugelb.themoviedb.view.model.ViewModelMovie
import com.neugelb.themoviedb.view.model.ViewModelMovies
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ModuleViewModel {

    @ScopeMain
    @Provides
    fun viewModelFavourites(
        application: TheMovieDbApplication,
        compositeDisposable: CompositeDisposable,
        logHelper: LogHelper,
        usecaseSavedMovies: UsecaseSavedMovies
    ): ViewModelFavourites {
        return ViewModelFavourites(application, compositeDisposable, logHelper, usecaseSavedMovies)
    }

    @ScopeMain
    @Provides
    fun viewModelMovie(
        application: TheMovieDbApplication,
        compositeDisposable: CompositeDisposable,
        logHelper: LogHelper,
        usecaseToggleSaveMovie: UsecaseToggleSaveMovie
    ): ViewModelMovie {
        return ViewModelMovie(application, compositeDisposable, logHelper, usecaseToggleSaveMovie)
    }

    @ScopeMain
    @Provides
    fun viewModelMovies(
        application: TheMovieDbApplication,
        compositeDisposable: CompositeDisposable,
        logHelper: LogHelper,
        usecaseFetchMovies: UsecaseFetchMovies,
        usecaseSearchMovies: UsecaseSearchMovies
    ): ViewModelMovies {
        return ViewModelMovies(application, compositeDisposable, logHelper, usecaseFetchMovies, usecaseSearchMovies)
    }

}