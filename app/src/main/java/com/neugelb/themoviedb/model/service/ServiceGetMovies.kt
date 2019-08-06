package com.neugelb.themoviedb.model.service

import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.external.rx.SchedulersProvider
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import com.neugelb.themoviedb.model.entity.Source
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import io.reactivex.Single
import javax.inject.Inject

@ScopeMain
class ServiceGetMovies
@Inject
constructor(
    val schedulersProvider: SchedulersProvider,
    val repositoryMovies: RepositoryMovies
) :
    ServiceBase<ServiceGetMovies.Input, Page<Movie>>(schedulersProvider) {

    override fun build(i: Input): Single<Page<Movie>> {
        return when (i.source) {
            Source.LATEST -> repositoryMovies.latest(i.page)
            Source.UPCOMING -> repositoryMovies.upcoming(i.page)
            Source.NOW_PLAYING -> repositoryMovies.nowPlaying(i.page)
            Source.POPULAR -> repositoryMovies.popular(i.page)
            Source.TOP_RATED -> repositoryMovies.topRated(i.page)
        }
    }

    data class Input(
        var page: Int,
        var source: Source
    )

}
