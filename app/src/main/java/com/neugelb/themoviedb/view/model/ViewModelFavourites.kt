package com.neugelb.themoviedb.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neugelb.themoviedb.TheMovieDbApplication
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.model.usecase.UsecaseSavedMovies
import io.reactivex.disposables.CompositeDisposable

class ViewModelFavourites(
    application: TheMovieDbApplication,
    compositeDisposable: CompositeDisposable,
    logHelper: LogHelper,
    private val usecaseSavedMovies: UsecaseSavedMovies
) : ViewModelBasePage(application, compositeDisposable, logHelper) {

    private val _allObservable: MutableLiveData<Response<Collection<Movie>>> = MutableLiveData()
    val allObservable: LiveData<Response<Collection<Movie>>>
        get() = _allObservable

    fun allMovies() {

        if (idle) {
            compositeDisposable.add(

                usecaseSavedMovies.execute(UsecaseSavedMovies.Input())
                    .doOnSubscribe { _allObservable.postValue(Response.loading()) }
                    .compose(composeSingle())
                    .subscribe(
                        { _allObservable.postValue(Response.success(it)) },
                        { _allObservable.postValue(Response.error(it)) }
                    )

            )
        }

    }


}
