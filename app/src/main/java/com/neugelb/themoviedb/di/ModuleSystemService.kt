package com.neugelb.themoviedb.di

import android.app.ActivityManager
import android.app.NotificationManager
import android.app.SearchManager
import android.content.Context
import android.hardware.SensorManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Vibrator
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.neugelb.themoviedb.external.dagger.ScopeMain
import dagger.Module
import dagger.Provides

@Module
open class ModuleSystemService {

    @ScopeMain
    @Provides
    open fun locationManager(context: Context): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @ScopeMain
    @Provides
    open fun connectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @ScopeMain
    @Provides
    open fun notificationManager(context: Context): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @ScopeMain
    @Provides
    open fun inputMethodManager(context: Context): InputMethodManager {
        return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    @ScopeMain
    @Provides
    open fun windowManager(context: Context): WindowManager {
        return context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    @ScopeMain
    @Provides
    open fun layoutInflater(context: Context): LayoutInflater {
        return context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    @ScopeMain
    @Provides
    open fun telephonyManager(context: Context): TelephonyManager {
        return context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    }

    @ScopeMain
    @Provides
    open fun searchManager(context: Context): SearchManager {
        return context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
    }

    @ScopeMain
    @Provides
    open fun vibrator(context: Context): Vibrator {
        return context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    @ScopeMain
    @Provides
    open fun activityManager(context: Context): ActivityManager {
        return context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    }

    @ScopeMain
    @Provides
    open fun sensorManager(context: Context): SensorManager {
        return context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

}