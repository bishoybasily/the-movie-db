package com.neugelb.themoviedb.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.model.service.ServiceToggleSaveMovie
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ViewModelMovie(
    compositeDisposable: CompositeDisposable,
    logHelper: LogHelper,
    private val serviceToggleSaveMovie: ServiceToggleSaveMovie
) :
    ViewModelBasePage(compositeDisposable, logHelper) {

    private val _toggleObservable: MutableLiveData<Response<Movie>> = MutableLiveData()
    val toggleObservable: LiveData<Response<Movie>>
        get() = _toggleObservable

    fun toggleSave(movie: Movie) {

        compositeDisposable.add(

            serviceToggleSaveMovie.execute(movie)
                .doOnSubscribe { _toggleObservable.postValue(Response.loading()) }
                .compose(composeSingle())
                .subscribe(
                    { _toggleObservable.postValue(Response.success(it)) },
                    { _toggleObservable.postValue(Response.error(it)) }
                )

        )

    }

    class Factory
    @Inject
    constructor(
        private val compositeDisposable: CompositeDisposable,
        private val logHelper: LogHelper,
        private val serviceToggleSaveMovie: ServiceToggleSaveMovie
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModelMovie::class.java))
                return ViewModelMovie(compositeDisposable, logHelper, serviceToggleSaveMovie) as T
            throw IllegalArgumentException()
        }

    }

}
