package com.neugelb.themoviedb.model.service

import com.neugelb.themoviedb.external.dagger.Network
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.external.rx.SchedulersProvider
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import io.reactivex.Single
import javax.inject.Inject

@ScopeMain
class ServiceSearchMovies
@Inject
constructor(
    val schedulersProvider: SchedulersProvider,
    @Network val repositoryMovies: RepositoryMovies
) :
    ServiceBase<ServiceSearchMovies.Input, Page<Movie>>(schedulersProvider) {

    override fun build(i: Input): Single<Page<Movie>> {
        throw UnsupportedOperationException()
    }

    class Input(
        var page: Int,
        var query: String
    )

}
