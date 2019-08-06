package com.neugelb.themoviedb.view.model

import androidx.lifecycle.ViewModel
import com.neugelb.themoviedb.helper.LogHelper
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable


abstract class ViewModelBase(
    private val composeDisposable: CompositeDisposable,
    private val logHelper: LogHelper
) :
    ViewModel() {

    protected var TAG = javaClass.simpleName

    protected var idle: Boolean = true

    fun <T> composeObservable(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it
                .doOnSubscribe { idle = false }
                .doAfterTerminate { idle = true }
                .doOnError { idle = true; logHelper.error(TAG, it) }
        }
    }

    fun <T> composeSingle(): SingleTransformer<T, T> {
        return SingleTransformer {
            it
                .doOnSubscribe { idle = false }
                .doAfterTerminate { idle = true }
                .doOnError { idle = true; logHelper.error(TAG, it) }
        }
    }

    final override fun onCleared() {
        cleared()
        composeDisposable.clear()
        super.onCleared()

    }

    open fun cleared() {

    }

}
