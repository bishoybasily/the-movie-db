package com.neugelb.themoviedb.di

import android.content.Context
import androidx.room.Room
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.model.data.DatabaseTheMovieDb
import dagger.Module
import dagger.Provides

@Module
class ModuleRoom {

    @ScopeMain
    @Provides
    fun database(context: Context): DatabaseTheMovieDb {
        return Room.databaseBuilder(context, DatabaseTheMovieDb::class.java, "theMovieDb.db")
            .build()
    }

}