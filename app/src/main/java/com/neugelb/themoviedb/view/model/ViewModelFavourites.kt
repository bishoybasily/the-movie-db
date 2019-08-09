package com.neugelb.themoviedb.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.model.service.ServiceSavedMovies
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ViewModelFavourites(
    compositeDisposable: CompositeDisposable,
    logHelper: LogHelper,
    private val serviceSavedMovies: ServiceSavedMovies
) :
    ViewModelBasePage(compositeDisposable, logHelper) {

    private val _allObservable: MutableLiveData<Response<Collection<Movie>>> = MutableLiveData()
    val allObservable: LiveData<Response<Collection<Movie>>>
        get() = _allObservable

    fun allMovies() {

        if (idle) {
            compositeDisposable.add(

                serviceSavedMovies.execute(ServiceSavedMovies.Input())
                    .doOnSubscribe { _allObservable.postValue(Response.loading()) }
                    .compose(composeSingle())
                    .subscribe(
                        { _allObservable.postValue(Response.success(it)) },
                        { _allObservable.postValue(Response.error(it)) }
                    )

            )
        }

    }

    class Factory
    @Inject
    constructor(
        private val compositeDisposable: CompositeDisposable,
        private val logHelper: LogHelper,
        private val serviceSavedMovies: ServiceSavedMovies
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModelFavourites::class.java))
                return ViewModelFavourites(compositeDisposable, logHelper, serviceSavedMovies) as T
            throw IllegalArgumentException()
        }

    }

}
