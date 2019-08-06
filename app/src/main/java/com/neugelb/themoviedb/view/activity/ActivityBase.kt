package com.neugelb.themoviedb.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class ActivityBase : AppCompatActivity() {

    abstract fun getLayoutResourceId(): Int

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResourceId())

        create(savedInstanceState)
    }

    open fun create(savedInstanceState: Bundle?) {

    }

    open fun destroy() {

    }

    final override fun onDestroy() {
        destroy()
        super.onDestroy()
    }

}
