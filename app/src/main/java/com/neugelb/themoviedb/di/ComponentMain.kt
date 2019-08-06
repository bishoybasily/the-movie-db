package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.ApplicationTheMovieDb
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.model.exception.ComponentNotInitializedThrowable
import dagger.Component

@ScopeMain
@Component(
    modules = [
        ModuleApplication::class,
        ModuleRX::class,
        ModuleUI::class,
        ModuleSystemService::class,
        ModuleNetwork::class,
        ModuleGson::class,
        ModuleRoom::class
    ]
)
interface ComponentMain {

    fun inject(applicationTheMovieDb: ApplicationTheMovieDb)

    companion object {

        private lateinit var component: ComponentMain

        fun initialize(applicationTheMovieDb: ApplicationTheMovieDb): ComponentMain {
            component = DaggerComponentMain.builder()
                .moduleApplication(ModuleApplication(applicationTheMovieDb))
                .build()
            return component
        }

        fun get() = if (Companion::component.isInitialized) component else throw ComponentNotInitializedThrowable()

    }


}
