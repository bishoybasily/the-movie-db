package com.neugelb.themoviedb.helper

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ResourcesHelper
@Inject
constructor(private val context: Context) {

    fun string(resourceId: Int): String {
        return context.getString(resourceId)
    }

    fun color(resourceId: Int): Int {
        return ContextCompat.getColor(context, resourceId)
    }

    fun animation(resourceId: Int): Animation {
        return AnimationUtils.loadAnimation(context, resourceId)
    }

    fun drawable(resourceId: Int): Drawable {
        return context.resources.getDrawable(resourceId)
    }

    fun bitmap(resourceId: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, resourceId)
    }

    fun integer(resourceId: Int): Int {
        return context.resources.getInteger(resourceId)
    }

    fun colorStateList(resourceId: Int): ColorStateList {
        return context.resources.getColorStateList(resourceId)
    }
}
