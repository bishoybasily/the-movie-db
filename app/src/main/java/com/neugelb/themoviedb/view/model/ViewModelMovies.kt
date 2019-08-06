package com.neugelb.themoviedb.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.model.service.ServiceFetchMovies
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ViewModelMovies(
    compositeDisposable: CompositeDisposable,
    logHelper: LogHelper,
    private val serviceFetchMovies: ServiceFetchMovies
) :
    ViewModelBasePage(compositeDisposable, logHelper) {

    private val _firstObservable: MutableLiveData<Response<Collection<Movie>>> = MutableLiveData()
    val firstObservable: LiveData<Response<Collection<Movie>>>
        get() = _firstObservable

    private val _nextObservable: MutableLiveData<Response<Collection<Movie>>> = MutableLiveData()
    val nextObservable: LiveData<Response<Collection<Movie>>>
        get() = _nextObservable

    fun firstMovies(source: Source) {
        if (idle) {
            compositeDisposable.add(

                serviceFetchMovies.execute(ServiceFetchMovies.Input(firstPage(), source))
                    .doOnSubscribe { _firstObservable.value = Response.loading() }
                    .compose(composeSinglePage())
                    .map { it.results }
                    .subscribe(
                        { _firstObservable.value = Response.success(it) },
                        { _firstObservable.value = Response.error(it) }

                    )

            )
        }
    }

    fun nextMovies(source: Source) {
        if (idle && hasMore) {
            compositeDisposable.add(

                serviceFetchMovies.execute(ServiceFetchMovies.Input(nextPage(), source))
                    .doOnSubscribe { _nextObservable.value = Response.loading() }
                    .compose(composeSinglePage())
                    .map { it.results }
                    .subscribe(
                        { _nextObservable.value = Response.success(it) },
                        { _nextObservable.value = Response.error(it) }

                    )

            )
        }
    }

    class Factory
    @Inject
    constructor(
        private val compositeDisposable: CompositeDisposable,
        private val logHelper: LogHelper,
        private val serviceFetchMovies: ServiceFetchMovies
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModelMovies::class.java))
                return ViewModelMovies(compositeDisposable, logHelper, serviceFetchMovies) as T
            throw IllegalArgumentException()
        }

    }

}
