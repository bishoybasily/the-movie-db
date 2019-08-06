package com.neugelb.themoviedb.model.service

import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.external.rx.SchedulersProvider
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import io.reactivex.Single
import javax.inject.Inject

@ScopeMain
class ServiceSavedMovies
@Inject
constructor(
    val schedulersProvider: SchedulersProvider,
    @Local val repositoryMovies: RepositoryMovies
) :
    ServiceBase<Int, Page<Movie>>(schedulersProvider) {

    override fun build(i: Int): Single<Page<Movie>> {
        return repositoryMovies.saved(i)
    }

}
