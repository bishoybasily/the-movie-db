package com.neugelb.themoviedb.model.usecase

import com.neugelb.themoviedb.di.ComponentMainTest
import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.external.dagger.Network
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.model.repository.RepositoryMovie
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
class UsecaseFetchMoviesTest {

    @field:[Inject]
    lateinit var usecaseFetchMovies: UsecaseFetchMovies
    @field:[Inject Network]
    lateinit var repositoryMovies: RepositoryMovies
    @field:[Inject Local]
    lateinit var repositoryMovie: RepositoryMovie

    val page = 1
    val totalPages = 1

    val upcoming = ArrayList<Movie>()
    val popular = ArrayList<Movie>()

    lateinit var pageUpcoming: Page<Movie>
    lateinit var pagePopular: Page<Movie>

    @Before
    fun setUp() {

        ComponentMainTest.initialize().inject(this)

        for (i in 0..5)
            upcoming.add(Movie("upcoming_$i"))
        pageUpcoming = Page(upcoming, page, totalPages)
        Mockito.`when`(repositoryMovies.upcoming(page)).thenReturn(Single.just(pageUpcoming))

        for (i in 0..5)
            popular.add(Movie("popular_$i"))
        pagePopular = Page(popular, page, totalPages)
        Mockito.`when`(repositoryMovies.popular(page)).thenReturn(Single.just(pagePopular))

    }

    @Test
    fun fetchPopularSaved() {

        val testObserver = TestObserver<Page<Movie>>()

        val id = "some_id"

        Mockito.`when`(repositoryMovie.find(Mockito.anyString())).thenReturn(Single.just(Movie(id)))

        usecaseFetchMovies.execute(UsecaseFetchMovies.Input(page, Source.POPULAR)).subscribeWith(testObserver)

        testObserver.assertValue {

            if (pagePopular !== it)
                return@assertValue false

            for (mov in it.results)
                if (!mov.saved)
                    return@assertValue false

            return@assertValue true

        }
        testObserver.assertComplete()

        Mockito.verify(repositoryMovies).popular(page)

    }

    @Test
    fun fetchUpcomingNotSaved() {

        val testObserver = TestObserver<Page<Movie>>()

        Mockito.`when`(repositoryMovie.find(Mockito.anyString())).thenReturn(Single.error(RuntimeException()))

        usecaseFetchMovies.execute(UsecaseFetchMovies.Input(page, Source.UPCOMING)).subscribeWith(testObserver)

        testObserver.assertValue {

            if (pageUpcoming !== it)
                return@assertValue false

            for (mov in it.results)
                if (mov.saved)
                    return@assertValue false

            return@assertValue true

        }
        testObserver.assertComplete()

        Mockito.verify(repositoryMovies).upcoming(page)

    }

}