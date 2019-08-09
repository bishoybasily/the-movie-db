package com.neugelb.themoviedb.di

import android.app.Application
import android.content.Context
import com.neugelb.themoviedb.ApplicationTheMovieDb
import org.mockito.Mockito

class ModuleApplicationTest : ModuleApplication(Mockito.mock(ApplicationTheMovieDb::class.java)) {

    override fun applicationTheMovieDb(): ApplicationTheMovieDb = Mockito.mock(ApplicationTheMovieDb::class.java)

    override fun application(): Application = Mockito.mock(ApplicationTheMovieDb::class.java)

    override fun context(): Context = Mockito.mock(ApplicationTheMovieDb::class.java)

}