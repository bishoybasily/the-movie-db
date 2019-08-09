package com.neugelb.themoviedb.di

import android.content.Context
import android.net.ConnectivityManager
import android.view.WindowManager
import org.mockito.Mockito

class ModuleSystemTest : ModuleSystem() {

    override fun connectivityManager(context: Context): ConnectivityManager {
        return Mockito.mock(ConnectivityManager::class.java)
    }

    override fun windowManager(context: Context): WindowManager {
        return Mockito.mock(WindowManager::class.java)
    }

}
