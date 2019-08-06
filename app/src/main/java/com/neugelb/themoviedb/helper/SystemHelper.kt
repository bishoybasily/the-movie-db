package com.neugelb.themoviedb.helper

import android.util.DisplayMetrics
import javax.inject.Inject

class SystemHelper
@Inject
constructor(
    private val displayMetrics: DisplayMetrics
) {

    fun dpTOpx(dp: Float): Int {
        return (dp * (displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    fun pxTOdp(px: Float): Int {
        return (px / (displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

}