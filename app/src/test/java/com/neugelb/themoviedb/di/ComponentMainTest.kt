package com.neugelb.themoviedb.di

import com.neugelb.themoviedb.ExampleUnitTest
import com.neugelb.themoviedb.external.dagger.ScopeMain
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
        ModuleDAO::class
    ]
)
interface ComponentMainTest {

    fun inject(arg: ExampleUnitTest)

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
