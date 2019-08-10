package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.external.rx.SchedulersProvider
import io.reactivex.schedulers.Schedulers


class ModuleRXTest : ModuleRX() {

    override fun schedulerProvider(): SchedulersProvider {
        return SchedulersProvider(
            Schedulers.trampoline(),
            Schedulers.trampoline(),
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

}