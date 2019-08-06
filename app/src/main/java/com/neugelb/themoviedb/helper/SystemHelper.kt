package com.neugelb.themoviedb.helper

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.DisplayMetrics
import java.util.*
import javax.inject.Inject

class SystemHelper
@Inject
constructor(
    private val context: Context,
    private val packageManager: PackageManager,
    private val activityManager: ActivityManager,
    private val displayMetrics: DisplayMetrics
) {

    fun isIntentResolvable(intent: Intent): Boolean {
        return intent.resolveActivity(packageManager) != null
    }

    fun dpTOpx(dp: Float): Int {
        return (dp * (displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    fun pxTOdp(px: Float): Int {
        return (px / (displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    fun locale(): Locale {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.resources.configuration.locales.get(0)
        } else {
            return context.resources.configuration.locale
        }
    }

    fun isServiceRunning(cls: String): Boolean {
        val services = activityManager.getRunningServices(Integer.MAX_VALUE)
        for (runningServiceInfo in services)
            if (runningServiceInfo.service.className.equals(cls))
                return true
        return false
    }

    fun startSingletonService(context: Context, cls: Class<*>) {
        if (isServiceRunning(cls.name))
            return
        context.startService(Intent(context, cls))
    }

}