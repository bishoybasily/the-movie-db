package com.neugelb.themoviedb.model.usecase

import com.neugelb.themoviedb.di.ComponentMainTest
import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

class UsecaseToggleSaveMovieTest {

    @field:[Inject]
    lateinit var usecaseToggleSaveMovie: UsecaseToggleSaveMovie
    @field:[Inject Local]
    lateinit var repositoryMovie: RepositoryMovie

    @Before
    fun setUp() {

        ComponentMainTest.initialize().inject(this)

    }

    @Test
    fun toggleSave() {

        val id = "some_id"
        val movie = Movie(id)

        Mockito.`when`(repositoryMovie.save(movie)).thenReturn(Single.just(movie))
        Mockito.`when`(repositoryMovie.delete(movie)).thenReturn(Single.just(movie))

        val testObserverDelete = TestObserver<Movie>()

        Mockito.`when`(repositoryMovie.find(Mockito.anyString())).thenReturn(Single.just(movie))

        usecaseToggleSaveMovie.execute(movie).subscribeWith(testObserverDelete)
        testObserverDelete.assertValue(movie)
        testObserverDelete.assertComplete()

        Mockito.verify(repositoryMovie).delete(movie)

        val testObserverSave = TestObserver<Movie>()

        Mockito.`when`(repositoryMovie.find(Mockito.anyString())).thenReturn(Single.error(RuntimeException()))

        usecaseToggleSaveMovie.execute(movie).subscribeWith(testObserverSave)
        testObserverSave.assertValue(movie)
        testObserverSave.assertComplete()

        Mockito.verify(repositoryMovie).save(movie)

    }

}