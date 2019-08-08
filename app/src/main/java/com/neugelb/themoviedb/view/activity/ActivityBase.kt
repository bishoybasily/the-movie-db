package com.neugelb.themoviedb.view.activity

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.neugelb.themoviedb.di.ComponentMain
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class ActivityBase : AppCompatActivity() {

    @field:[Inject]
    lateinit var componeDisposable: CompositeDisposable

    abstract fun getLayoutResourceId(): Int

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentMain.get().inject(this)

        setContentView(getLayoutResourceId())

        create(savedInstanceState)
    }

    final override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition()
                    return true
                }
            }
        }
        return optionsItemSelected(item)
    }


    open fun optionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    open fun create(savedInstanceState: Bundle?) {

    }

    open fun destroy() {

    }

    final override fun onDestroy() {
        componeDisposable.clear()
        destroy()
        super.onDestroy()
    }

}
