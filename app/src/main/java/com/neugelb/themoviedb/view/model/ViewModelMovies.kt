package com.neugelb.themoviedb.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.model.usecase.UsecaseFetchMovies
import com.neugelb.themoviedb.model.usecase.UsecaseSearchMovies
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ViewModelMovies(
    compositeDisposable: CompositeDisposable,
    logHelper: LogHelper,
    private val usecaseFetchMovies: UsecaseFetchMovies,
    private val usecaseSearchMovies: UsecaseSearchMovies
) :
    ViewModelBasePage(compositeDisposable, logHelper) {

    private val _firstObservable: MutableLiveData<Response<Collection<Movie>>> = MutableLiveData()
    val firstObservable: LiveData<Response<Collection<Movie>>>
        get() = _firstObservable

    private val _nextObservable: MutableLiveData<Response<Collection<Movie>>> = MutableLiveData()
    val nextObservable: LiveData<Response<Collection<Movie>>>
        get() = _nextObservable

    private val _firstSearchObservable: MutableLiveData<Response<Collection<Movie>>> = MutableLiveData()
    val firstSearchObservable: LiveData<Response<Collection<Movie>>>
        get() = _firstSearchObservable

    private val _nextSearchObservable: MutableLiveData<Response<Collection<Movie>>> = MutableLiveData()
    val nextSearchObservable: LiveData<Response<Collection<Movie>>>
        get() = _nextSearchObservable

    lateinit var source: Source

    lateinit var query: String

    fun firstMovies(s: Source? = null) {

        if (s != null)
            this.source = s

        if (::source.isInitialized)
            if (idle) {
                compositeDisposable.add(

                    usecaseFetchMovies.execute(UsecaseFetchMovies.Input(firstPage(), source))
                        .doOnSubscribe { _firstObservable.postValue(Response.loading()) }
                        .compose(composeSinglePage())
                        .map { it.results }
                        .subscribe(
                            { _firstObservable.postValue(Response.success(it)) },
                            { _firstObservable.postValue(Response.error(it)) }
                        )

                )
            }
    }

    fun nextMovies() {

        if (::source.isInitialized)
            if (idle && hasMore) {
                compositeDisposable.add(

                    usecaseFetchMovies.execute(UsecaseFetchMovies.Input(nextPage(), source))
                        .doOnSubscribe { _nextObservable.postValue(Response.loading()) }
                        .compose(composeSinglePage())
                        .map { it.results }
                        .subscribe(
                            { _nextObservable.postValue(Response.success(it)) },
                            { _nextObservable.postValue(Response.error(it)) }
                        )

                )
            }
    }

    //**

    fun firstSearchMovies(q: String? = null) {
        if (q != null)
            query = q

        if (::query.isInitialized)
            if (idle) {
                compositeDisposable.add(

                    usecaseSearchMovies.execute(UsecaseSearchMovies.Input(firstPage(), query))
                        .doOnSubscribe { _firstSearchObservable.postValue(Response.loading()) }
                        .compose(composeSinglePage())
                        .map { it.results }
                        .subscribe(
                            { _firstSearchObservable.postValue(Response.success(it)) },
                            { _firstSearchObservable.postValue(Response.error(it)) }
                        )

                )
            }
    }

    fun nextSearchMovies() {
        if (::query.isInitialized)
            if (idle && hasMore) {
                compositeDisposable.add(

                    usecaseSearchMovies.execute(UsecaseSearchMovies.Input(nextPage(), query))
                        .doOnSubscribe { _nextSearchObservable.postValue(Response.loading()) }
                        .compose(composeSinglePage())
                        .map { it.results }
                        .subscribe(
                            { _nextSearchObservable.postValue(Response.success(it)) },
                            { _nextSearchObservable.postValue(Response.error(it)) }
                        )

                )
            }
    }

    class Factory
    @Inject
    constructor(
        private val compositeDisposable: CompositeDisposable,
        private val logHelper: LogHelper,
        private val usecaseFetchMovies: UsecaseFetchMovies,
        private val usecaseSearchMovies: UsecaseSearchMovies
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModelMovies::class.java))
                return ViewModelMovies(compositeDisposable, logHelper, usecaseFetchMovies, usecaseSearchMovies) as T
            throw IllegalArgumentException()
        }

    }

}
