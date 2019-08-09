package com.neugelb.themoviedb.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.neugelb.themoviedb.R
import kotlinx.android.synthetic.main.activity_favourites.*

class ActivityFavourites : ActivityBase() {

    companion object {

        fun start(activity: ComponentActivity) {
            Intent(activity, ActivityFavourites::class.java).also(activity::startActivity)
        }

    }

    override fun getLayoutResourceId() = R.layout.activity_favourites

    override fun create(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
