package com.neugelb.themoviedb.model.service


import com.neugelb.themoviedb.external.rx.SchedulersProvider
import io.reactivex.Single

abstract class ServiceBase<I, O>(private val schedulersProvider: SchedulersProvider) {

    protected abstract fun build(i: I): Single<O>

    fun execute(i: I): Single<O> {
        return build(i)
            .subscribeOn(schedulersProvider.subscribe)
            .observeOn(schedulersProvider.observe)
            .subscribeOn(schedulersProvider.unsubscribe)
    }

}
