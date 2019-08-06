package com.neugelb.themoviedb.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import javax.inject.Inject

class NetworkHelper
@Inject
constructor(
    val context: Context,
    val connectivityManager: ConnectivityManager
) {

    val isInternetAvailable: Boolean
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) return checkNetwork()
            else return checkNetworkLegacy()
        }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun checkNetwork(): Boolean {
        for (network in connectivityManager.allNetworks) {
            val info = connectivityManager.getNetworkInfo(network)
            if (info != null)
                if (info.state == NetworkInfo.State.CONNECTED)
                    return true
        }
        return false
    }

    private fun checkNetworkLegacy(): Boolean {
        val info = connectivityManager.allNetworkInfo
        if (info != null)
            for (i in info.indices)
                if (info[i].state == NetworkInfo.State.CONNECTED)
                    return true
        return false
    }

}
