package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.ApplicationTheMovieDb
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.view.activity.ActivityBase
import com.neugelb.themoviedb.view.activity.ActivityHome
import com.neugelb.themoviedb.view.activity.ActivityMovie
import com.neugelb.themoviedb.view.fragment.FragmentBase
import com.neugelb.themoviedb.view.fragment.FragmentMovies
import com.neugelb.themoviedb.view.fragment.FragmentMoviesSearch
import com.neugelb.themoviedb.view.holder.ViewHolderMovie
import com.neugelb.themoviedb.view.holder.ViewHolderMovieCompat
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
        ModuleRoom::class,
        ModuleRepository::class,
        ModuleDAO::class
    ]
)
interface ComponentMain {

    fun inject(arg: ApplicationTheMovieDb)
    fun inject(arg: FragmentMovies)
    fun inject(arg: ViewHolderMovie)
    fun inject(arg: ActivityMovie)
    fun inject(arg: ActivityHome)
    fun inject(arg: ActivityBase)
    fun inject(arg: ViewHolderMovieCompat)
    fun inject(arg: FragmentMoviesSearch)
    fun inject(arg: FragmentBase)

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
