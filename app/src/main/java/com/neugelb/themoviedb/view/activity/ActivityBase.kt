package com.neugelb.themoviedb.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neugelb.themoviedb.R

abstract class ActivityBase : AppCompatActivity() {

    abstract fun getLayoutResourceId(): Int

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

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
