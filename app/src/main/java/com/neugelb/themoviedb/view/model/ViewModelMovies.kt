package com.neugelb.themoviedb.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelMovies : ViewModel() {


    class Factory
    @Inject
    constructor() :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModelMovies::class.java))
                return ViewModelMovies() as T
            throw IllegalArgumentException()
        }

    }

}
