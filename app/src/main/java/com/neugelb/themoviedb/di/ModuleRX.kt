package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.external.rx.SchedulersProvider
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@Module
class ModuleRX {

    @Provides
    fun compositeDisposable() = CompositeDisposable()

    @Provides
    fun schedulerProvider(): SchedulersProvider {
        return SchedulersProvider(Schedulers.io(), AndroidSchedulers.mainThread(), Schedulers.computation())
    }

}