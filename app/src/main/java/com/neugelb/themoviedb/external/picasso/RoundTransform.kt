package com.neugelb.themoviedb.external.picasso

import android.graphics.*
import com.squareup.picasso.Transformation

class RoundTransform(var func: (Int) -> Float = { it / 2f }) : Transformation {

    override fun transform(source: Bitmap): Bitmap {

        val size = Math.min(source.width, source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source)
            source.recycle()

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true

        val r = func(size)
        canvas.drawCircle(r, r, r, paint)

        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "round"
    }

}