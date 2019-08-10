package com.neugelb.themoviedb.model.usecase

import com.neugelb.themoviedb.external.dagger.Network
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.external.rx.SchedulersProvider
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import com.neugelb.themoviedb.model.usecase.function.MapperMoviesStatuses
import io.reactivex.Single
import javax.inject.Inject

@ScopeMain
class UsecaseSearchMovies
@Inject
constructor(
    val schedulersProvider: SchedulersProvider,
    @Network val repositoryMovies: RepositoryMovies,
    val mapperMoviesStatuses: MapperMoviesStatuses
) :
    UsecaseBase<UsecaseSearchMovies.Input, Page<Movie>>(schedulersProvider) {

    override fun build(i: Input): Single<Page<Movie>> {
        return repositoryMovies.search(i.query, i.page)
            .flatMap {
                mapperMoviesStatuses.apply(it)
            }
    }

    class Input(
        var page: Int,
        var query: String
    )

}
