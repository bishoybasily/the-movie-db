package com.neugelb.themoviedb.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neugelb.themoviedb.TheMovieDbApplication
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.model.usecase.UsecaseToggleSaveMovie
import io.reactivex.disposables.CompositeDisposable

class ViewModelMovie(
    application: TheMovieDbApplication,
    compositeDisposable: CompositeDisposable,
    logHelper: LogHelper,
    private val usecaseToggleSaveMovie: UsecaseToggleSaveMovie
) : ViewModelBasePage(application, compositeDisposable, logHelper) {

    private val _toggleObservable: MutableLiveData<Response<Movie>> = MutableLiveData()
    val toggleObservable: LiveData<Response<Movie>>
        get() = _toggleObservable

    fun toggleSave(movie: Movie) {

        compositeDisposable.add(

            usecaseToggleSaveMovie.execute(movie)
                .doOnSubscribe { _toggleObservable.postValue(Response.loading()) }
                .compose(composeSingle())
                .subscribe(
                    { _toggleObservable.postValue(Response.success(it)) },
                    { _toggleObservable.postValue(Response.error(it)) }
                )

        )

    }

}
