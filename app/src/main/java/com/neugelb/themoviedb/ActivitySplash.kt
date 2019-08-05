package com.neugelb.themoviedb

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_splash)

        animationView.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationRepeat(animation: Animator?) {
                Log.w("##", "onAnimationRepeat")

            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.w("##", "Start home now")
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        animationView.playAnimation()

    }

}
