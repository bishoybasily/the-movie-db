package com.neugelb.themoviedb.model.usecase.function

import com.neugelb.themoviedb.di.ComponentMainTest
import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class MapperMoviesStatusesTest {

    @field:[Inject Local]
    lateinit var repositoryMovie: RepositoryMovie
    @field:[Inject]
    lateinit var mapperMoviesStatuses: MapperMoviesStatuses

    @Before
    fun setUp() {
        ComponentMainTest.initialize().inject(this)
    }

    @Test
    fun searchResultsMixedStatuses() {

        val mov1Id = "mov1"
        val mov2Id = "mov2"
        val mov3Id = "mov3"

        val page = Page(listOf(Movie(mov1Id), Movie(mov2Id), Movie(mov3Id)), 1, 1)

        Mockito.`when`(repositoryMovie.find(mov1Id)).thenReturn(Single.just(Movie(mov1Id)))
        Mockito.`when`(repositoryMovie.find(mov2Id)).thenReturn(Single.error(RuntimeException()))
        Mockito.`when`(repositoryMovie.find(mov3Id)).thenReturn(Single.just(Movie(mov3Id)))

        val testObserver = TestObserver<Page<Movie>>()

        mapperMoviesStatuses.apply(page).subscribeWith(testObserver)

        testObserver.assertValue {

            val results = it.results

            return@assertValue results[0].saved && !results[1].saved && results[2].saved && results.size == 3

        }

    }
}
