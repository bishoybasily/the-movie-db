package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.external.rx.SchedulersProvider
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@Module
open class ModuleRX {

    @Provides
    open fun compositeDisposable() = CompositeDisposable()

    @Provides
    open fun schedulerProvider(): SchedulersProvider {
        return SchedulersProvider(Schedulers.io(), AndroidSchedulers.mainThread(), Schedulers.computation())
    }

}