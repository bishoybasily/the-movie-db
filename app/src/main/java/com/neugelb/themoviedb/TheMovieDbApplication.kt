package com.neugelb.themoviedb

import android.app.Application
import com.neugelb.themoviedb.di.ComponentMain
import com.neugelb.themoviedb.helper.LogHelper
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

open class TheMovieDbApplication : Application() {

    val TAG = javaClass.simpleName

    @Inject
    lateinit var logHelper: LogHelper

    override fun onCreate() {
        super.onCreate()

        initDI()
        initSDKs()

    }

    private fun initDI() {
        ComponentMain.initialize(this).inject(this)
    }

    private fun initSDKs() {
        RxJavaPlugins.setErrorHandler { logHelper.error(TAG, it) }
    }

}
