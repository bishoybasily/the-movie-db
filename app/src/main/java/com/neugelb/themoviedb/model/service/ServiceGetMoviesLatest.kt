package com.neugelb.themoviedb.model.service

import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.external.rx.SchedulersProvider
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.entity.Page
import io.reactivex.Single
import javax.inject.Inject

@ScopeMain
class ServiceGetMoviesLatest
@Inject
constructor(val schedulersProvider: SchedulersProvider) :
    ServiceBase<Int, Page<Movie>>(schedulersProvider) {

    override fun build(i: Int): Single<Page<Movie>> {
        return Single.error(RuntimeException())
    }

}
