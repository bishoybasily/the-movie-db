package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.TheMovieDbApplication
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.view.activity.ActivityBase
import com.neugelb.themoviedb.view.activity.ActivityFavourites
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
        ModuleSystem::class,
        ModuleNetwork::class,
        ModuleGson::class,
        ModuleRoom::class,
        ModuleRepository::class,
        ModuleDao::class,
        ModuleViewModel::class
    ]
)
interface ComponentMain {

    fun inject(arg: TheMovieDbApplication)
    fun inject(arg: ActivityMovie)
    fun inject(arg: ActivityHome)
    fun inject(arg: ActivityBase)
    fun inject(arg: ActivityFavourites)
    fun inject(arg: ViewHolderMovie)
    fun inject(arg: ViewHolderMovieCompat)
    fun inject(arg: FragmentMoviesSearch)
    fun inject(arg: FragmentMovies)
    fun inject(arg: FragmentBase)

    companion object {

        private lateinit var component: ComponentMain

        fun initialize(theMovieDbApplication: TheMovieDbApplication): ComponentMain {
            component = DaggerComponentMain.builder()
                .moduleApplication(ModuleApplication(theMovieDbApplication))
                .build()
            return component
        }

        fun get() = component

    }


}
