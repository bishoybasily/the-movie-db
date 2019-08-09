package com.neugelb.themoviedb

import android.net.ConnectivityManager
import com.neugelb.themoviedb.di.ComponentMainTest
import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @field:[Inject]
    lateinit var connectivityManager: ConnectivityManager
    @field:[Inject Local]
    lateinit var repositoryMovie: RepositoryMovie

    @Before
    fun before() {
        ComponentMainTest.initialize().inject(this)
    }

    @Test
    fun addition_isCorrect() {

        val id = "1"

        val movie = Movie(id)

        Mockito.`when`(repositoryMovie.find(id)).thenReturn(Single.just(movie))

        val testObserver = TestObserver<Movie>()

        repositoryMovie.find(id).subscribe(testObserver)

        testObserver.assertResult(movie)
        testObserver.assertComplete()

        assertEquals(4, 2 + 2)
        assertNotNull(connectivityManager)
    }

}
