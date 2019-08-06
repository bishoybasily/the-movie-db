package com.neugelb.themoviedb.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class FragmentBase : Fragment() {

    abstract fun getLayoutResourceId(): Int

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        create(savedInstanceState)
    }

    open fun create(savedInstanceState: Bundle?) {

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
        destroy()
        super.onDestroy()
    }

}
