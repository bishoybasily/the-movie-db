package com.neugelb.themoviedb.model.usecase

import com.neugelb.themoviedb.di.ComponentMainTest
import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class UsecaseSavedMoviesTest {

    @field:[Inject]
    lateinit var usecaseSavedMovies: UsecaseSavedMovies
    @field:[Inject Local]
    lateinit var repositoryMovies: RepositoryMovies

    val favourites = ArrayList<Movie>()

    @Before
    fun setUp() {

        ComponentMainTest.initialize().inject(this)

        for (i in 0..5)
            favourites.add(Movie("fav_$i"))

    }

    @Test
    fun getSaved() {

        val testObserver = TestObserver<List<Movie>>()

        Mockito.`when`(repositoryMovies.saved()).thenReturn(Single.just(favourites))

        usecaseSavedMovies.execute(UsecaseSavedMovies.Input()).subscribeWith(testObserver)

        testObserver.assertValue(favourites)
        testObserver.assertComplete()

        Mockito.verify(repositoryMovies).saved()

    }
}