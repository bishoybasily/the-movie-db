package com.neugelb.themoviedb.helper

import android.util.Log
import com.neugelb.themoviedb.Constants
import javax.inject.Inject


class LogHelper
@Inject
constructor() {

    fun warn(tag: String, message: String?) {
        if (Constants.Configuration.LOGGING)
            Log.w(tag, message)
    }

    fun info(tag: String, message: String?) {
        if (Constants.Configuration.LOGGING)
            Log.i(tag, message)
    }

    fun error(tag: String, message: String?) {
        if (Constants.Configuration.LOGGING)
            Log.e(tag, message)
    }

    fun error(tag: String, throwable: Throwable?) {
        if (Constants.Configuration.LOGGING)
            Log.e(tag, throwable?.message, throwable)
    }

}
