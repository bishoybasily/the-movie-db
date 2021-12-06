package com.neugelb.themoviedb.view.model

import dagger.Lazy
import javax.inject.Inject

class SomeProvider<T>
@Inject
constructor(private val lazy: Lazy<T>) {

    fun create(modelClass: Class<T>): T {
        return lazy.get() as T
    }
}