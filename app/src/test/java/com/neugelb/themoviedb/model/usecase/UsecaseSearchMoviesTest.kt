package com.neugelb.themoviedb.model.usecase

import com.neugelb.themoviedb.di.ComponentMainTest
import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.external.dagger.Network
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
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
class UsecaseSearchMoviesTest {

    @field:[Inject]
    lateinit var usecaseSearchMovies: UsecaseSearchMovies
    @field:[Inject Network]
    lateinit var repositoryMovies: RepositoryMovies
    @field:[Inject Local]
    lateinit var repositoryMovie: RepositoryMovie

    val page = 1
    val totalPages = 1

    val searchResults = ArrayList<Movie>()
    lateinit var pageSearchResults: Page<Movie>

    @Before
    fun setUp() {

        ComponentMainTest.initialize().inject(this)

        for (i in 0..5)
            searchResults.add(Movie("search_result_$i"))
        pageSearchResults = Page(searchResults, page, totalPages)

    }

    @Test
    fun searchResultsSaved() {

        val testObserver = TestObserver<Page<Movie>>()

        val query = "some text"

        Mockito.`when`(repositoryMovies.search(query, page)).thenReturn(Single.just(pageSearchResults))

        val id = "some_id"

        Mockito.`when`(repositoryMovie.find(Mockito.anyString())).thenReturn(Single.just(Movie(id)))

        usecaseSearchMovies.execute(UsecaseSearchMovies.Input(page, query)).subscribeWith(testObserver)

        testObserver.assertValue {

            if (pageSearchResults !== it)
                return@assertValue false

            for (mov in it.results)
                if (!mov.saved)
                    return@assertValue false

            return@assertValue true

        }
        testObserver.assertComplete()

        Mockito.verify(repositoryMovies, Mockito.times(1)).search(query, page)

    }


}