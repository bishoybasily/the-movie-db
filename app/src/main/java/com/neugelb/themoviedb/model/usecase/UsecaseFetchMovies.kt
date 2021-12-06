package com.neugelb.themoviedb.model.usecase

import com.neugelb.themoviedb.external.dagger.Network
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.external.rx.SchedulersProvider
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import com.neugelb.themoviedb.model.usecase.function.MapperMoviesStatuses
import io.reactivex.Single
import javax.inject.Inject

@ScopeMain
class UsecaseFetchMovies
@Inject
constructor(
    val schedulersProvider: SchedulersProvider,
    @Network val repositoryMovies: RepositoryMovies,
    val mapperMoviesStatuses: MapperMoviesStatuses
) :
    UsecaseBase<UsecaseFetchMovies.Input, Page<Movie>>(schedulersProvider) {

    override fun build(i: Input): Single<Page<Movie>> {
        return when (i.source) {
            Source.UPCOMING -> repositoryMovies.upcoming(i.page)
            Source.NOW_PLAYING -> repositoryMovies.nowPlaying(i.page)
            Source.POPULAR -> repositoryMovies.popular(i.page)
            Source.TOP_RATED -> repositoryMovies.topRated(i.page)
        }.flatMap(mapperMoviesStatuses::apply)
    }

    class Input(
        var page: Int,
        var source: Source
    )

}
