package com.neugelb.themoviedb.view.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.neugelb.themoviedb.di.ComponentMainTest
import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Response
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import com.neugelb.themoviedb.model.usecase.UsecaseToggleSaveMovie
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import javax.inject.Inject

class ViewModelMovieTest {

    @field:[Inject]
    lateinit var viewModelMovie: ViewModelMovie
    @field:[Inject]
    lateinit var compositeDisposable: CompositeDisposable
    @field:[Inject]
    lateinit var logHelper: LogHelper
    @field:[Inject]
    lateinit var usecaseToggleSaveMovie: UsecaseToggleSaveMovie
    @field:[Inject Local]
    lateinit var repositoryMovie: RepositoryMovie

    @Mock
    lateinit var observer: Observer<Response<Movie>>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        ComponentMainTest.initialize().inject(this)

        MockitoAnnotations.initMocks(this)

        viewModelMovie.toggleObservable.observeForever(observer)

    }

    @Test
    fun toggleSave_SaveIt() {

        val id = "some_id"
        val movie = Movie(id)

        Mockito.`when`(repositoryMovie.save(movie)).thenReturn(Single.just(movie))
        Mockito.`when`(repositoryMovie.delete(movie)).thenReturn(Single.just(movie))

        Mockito.`when`(repositoryMovie.find(id)).thenReturn(Single.just(movie))

        viewModelMovie.toggleSave(movie)

        Mockito.verify(observer).onChanged(Response.loading())
        Mockito.verify(observer).onChanged(Response.success(movie))

        Mockito.verify(repositoryMovie).delete(movie)

    }


    @Test
    fun toggleSave_DeleteIt() {

        val id = "some_id"
        val movie = Movie(id)

        Mockito.`when`(repositoryMovie.save(movie)).thenReturn(Single.just(movie))
        Mockito.`when`(repositoryMovie.delete(movie)).thenReturn(Single.just(movie))

        Mockito.`when`(repositoryMovie.find(id)).thenReturn(Single.error(RuntimeException()))

        viewModelMovie.toggleSave(movie)

        Mockito.verify(observer).onChanged(Response.loading())
        Mockito.verify(observer).onChanged(Response.success(movie))

        Mockito.verify(repositoryMovie).save(movie)

    }

    @After
    fun tearDown() {
        viewModelMovie.cleared()
    }

}