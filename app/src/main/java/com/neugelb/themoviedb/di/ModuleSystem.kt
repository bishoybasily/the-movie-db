package com.neugelb.themoviedb.di

import android.content.Context
import android.net.ConnectivityManager
import android.view.WindowManager
import com.neugelb.themoviedb.external.dagger.ScopeMain
import dagger.Module
import dagger.Provides

@Module
open class ModuleSystem {

    @ScopeMain
    @Provides
    open fun connectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @ScopeMain
    @Provides
    open fun windowManager(context: Context): WindowManager {
        return context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

}