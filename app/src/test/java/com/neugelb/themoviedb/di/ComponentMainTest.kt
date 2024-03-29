package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.model.usecase.UsecaseFetchMoviesTest
import com.neugelb.themoviedb.model.usecase.UsecaseSavedMoviesTest
import com.neugelb.themoviedb.model.usecase.UsecaseSearchMoviesTest
import com.neugelb.themoviedb.model.usecase.UsecaseToggleSaveMovieTest
import com.neugelb.themoviedb.model.usecase.function.MapperMoviesStatusesTest
import com.neugelb.themoviedb.view.model.ViewModelFavouritesTest
import com.neugelb.themoviedb.view.model.ViewModelMovieTest
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
interface ComponentMainTest {

    fun inject(arg: UsecaseFetchMoviesTest)
    fun inject(arg: UsecaseSearchMoviesTest)
    fun inject(arg: MapperMoviesStatusesTest)
    fun inject(arg: UsecaseToggleSaveMovieTest)
    fun inject(arg: UsecaseSavedMoviesTest)
    fun inject(arg: ViewModelFavouritesTest)
    fun inject(arg: ViewModelMovieTest)

    companion object {

        private lateinit var component: ComponentMainTest

        fun initialize(): ComponentMainTest {
            component = DaggerComponentMainTest.builder()
                .moduleApplication(ModuleApplicationTest())
                .moduleRX(ModuleRXTest())
                .moduleSystem(ModuleSystemTest())
                .moduleRepository(ModuleRepositoryTest())
                .build()
            return component
        }

        fun get() = component

    }


}
