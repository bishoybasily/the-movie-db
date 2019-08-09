package com.neugelb.themoviedb.model.service.function

import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ScopeMain
class MapperMoviesStatuses
@Inject
constructor(
    @Local private val repositoryMovie: RepositoryMovie
) :
    Function<Page<Movie>, Single<Page<Movie>>> {

    override fun apply(page: Page<Movie>): Single<Page<Movie>> {
        return Observable.fromIterable(page.results)
            .flatMapSingle { movie ->
                return@flatMapSingle repositoryMovie.find(movie.id) // try to find it in the db
                    .map { movie.saved = true; movie } // mark saved if found
                    .onErrorReturn { movie.saved = false; movie } // else mark not saved
                    .subscribeOn(Schedulers.newThread())
            }
            .toList()
            .map { page.results = it; page }
    }
}
