package com.neugelb.themoviedb.view.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

fun <VM : ViewModel> Fragment.viewModel(clazz: Class<VM>, factory: ViewModelProvider.Factory): VM {
    return ViewModelProviders.of(this, factory).get(clazz)
}

fun <VM : ViewModel> AppCompatActivity.viewModel(clazz: Class<VM>, factory: ViewModelProvider.Factory): VM {
    return ViewModelProviders.of(this, factory).get(clazz)
}