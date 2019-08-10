package com.neugelb.themoviedb.view.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.neugelb.themoviedb.di.ComponentMainTest
import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import com.neugelb.themoviedb.model.usecase.UsecaseSavedMovies
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject


@RunWith(MockitoJUnitRunner::class)
class ViewModelFavouritesTest {

    @field:[Inject]
    lateinit var compositeDisposable: CompositeDisposable
    @field:[Inject]
    lateinit var logHelper: LogHelper
    @field:[Inject]
    lateinit var usecaseSavedMovies: UsecaseSavedMovies
    @field:[Inject Local]
    lateinit var repositoryMovies: RepositoryMovies

    lateinit var viewModelFavourites: ViewModelFavourites

    val favourites = ArrayList<Movie>()

    @Mock
    lateinit var observer: Observer<Response<Collection<Movie>>>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        ComponentMainTest.initialize().inject(this)

        MockitoAnnotations.initMocks(this)

        for (i in 0..5)
            favourites.add(Movie("fav_$i"))

        viewModelFavourites = ViewModelFavourites(compositeDisposable, logHelper, usecaseSavedMovies)

        viewModelFavourites.allObservable.observeForever(observer)

    }

    @Test
    fun allMovies() {

        Mockito.`when`(repositoryMovies.saved()).thenReturn(Single.just(favourites))

        viewModelFavourites.allMovies()

        Mockito.verify(observer).onChanged(Response.loading())
        Mockito.verify(observer).onChanged(Response.success(favourites))

    }

    @After
    fun tearDown() {

        viewModelFavourites.cleared()
    }

}