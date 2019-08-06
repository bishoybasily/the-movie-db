package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.ApplicationTheMovieDb
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.view.activity.ActivityMovie
import com.neugelb.themoviedb.view.fragment.FragmentMovies
import com.neugelb.themoviedb.view.holder.ViewHolderMovie
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

    fun inject(arg: ApplicationTheMovieDb)
    fun inject(arg: FragmentMovies)
    fun inject(arg: ViewHolderMovie)
    fun inject(arg: ActivityMovie)

    companion object {

        private lateinit var component: ComponentMain

        fun initialize(applicationTheMovieDb: ApplicationTheMovieDb): ComponentMain {
            component = DaggerComponentMain.builder()
                .moduleApplication(ModuleApplication(applicationTheMovieDb))
                .build()
            return component
        }

        fun get() = component

    }


}
