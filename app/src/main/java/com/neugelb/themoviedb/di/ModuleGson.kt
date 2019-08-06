package com.neugelb.themoviedb.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.neugelb.themoviedb.external.dagger.ScopeMain
import dagger.Module
import dagger.Provides

@Module
class ModuleGson {

    @ScopeMain
    @Provides
    fun gson(): Gson {
        return GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create()
    }

}