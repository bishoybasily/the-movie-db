package com.neugelb.themoviedb.model.service

import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.external.rx.SchedulersProvider
import com.neugelb.themoviedb.helper.LogHelper
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.repository.RepositoryMovie
import io.reactivex.Single
import javax.inject.Inject

@ScopeMain
class ServiceToggleSaveMovie
@Inject
constructor(
    val logHelper: LogHelper,
    val schedulersProvider: SchedulersProvider,
    @Local val repositoryMovie: RepositoryMovie
) :
    ServiceBase<Movie, Movie>(schedulersProvider) {

    override fun build(i: Movie): Single<Movie> {
        return repositoryMovie.find(i.id)
            .flatMap { repositoryMovie.delete(it).map { it.saved = false; it } }
            .onErrorResumeNext { repositoryMovie.save(i).map { it.saved = true; it } }
    }

}
