package com.neugelb.themoviedb.di

import android.app.Application
import android.content.Context
import com.neugelb.themoviedb.TheMovieDbApplication
import org.mockito.Mockito

class ModuleApplicationTest : ModuleApplication(Mockito.mock(TheMovieDbApplication::class.java)) {

    override fun applicationTheMovieDb(): TheMovieDbApplication = Mockito.mock(TheMovieDbApplication::class.java)

    override fun application(): Application = Mockito.mock(TheMovieDbApplication::class.java)

    override fun context(): Context = Mockito.mock(TheMovieDbApplication::class.java)

}