package com.neugelb.themoviedb.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.neugelb.themoviedb.di.ComponentMain
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class FragmentBase : Fragment() {

    @field:[Inject]
    lateinit var compositeDisposable: CompositeDisposable

    abstract fun getLayoutResourceId(): Int

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentMain.get().inject(this)

        create(savedInstanceState)
    }

    open fun create(savedInstanceState: Bundle?) {
        val activity: FragmentActivity? = activity
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }


    open fun destroy() {

    }

    final override fun onDestroy() {
        compositeDisposable.clear()
        destroy()
        super.onDestroy()
    }

}
