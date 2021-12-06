package com.neugelb.themoviedb.view.model

import com.neugelb.themoviedb.TheMovieDbApplication
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Page
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable

abstract class ViewModelBasePage(
    application: TheMovieDbApplication,
    val compositeDisposable: CompositeDisposable,
    val logHelper: LogHelper
) : ViewModelBase(application, compositeDisposable, logHelper) {

    protected var page: Int = 1
    protected var hasMore: Boolean = true

    protected fun firstPage(): Int {
        page = 1
        return page
    }

    protected fun nextPage(): Int {
        page++
        return page
    }

    fun <T> composeSinglePage(): SingleTransformer<Page<T>, Page<T>> {
        return SingleTransformer {
            it
                .doOnSubscribe { idle = false }
                .doOnSuccess { hasMore = it.hasMore }
                .doAfterTerminate { idle = true }
                .doOnError { idle = true; logHelper.error(TAG, it) }
        }
    }

}
