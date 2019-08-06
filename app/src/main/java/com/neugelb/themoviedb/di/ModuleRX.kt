package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.external.rx.SchedulerProvider
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
    fun schedulerProvider(): SchedulerProvider {
        return SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread(), Schedulers.computation())
    }

}