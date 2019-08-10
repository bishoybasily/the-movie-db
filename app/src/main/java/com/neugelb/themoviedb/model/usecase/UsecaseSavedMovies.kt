package com.neugelb.themoviedb.model.usecase

import com.neugelb.themoviedb.external.dagger.Local
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.external.rx.SchedulersProvider
import com.neugelb.themoviedb.model.entity.Movie
import com.neugelb.themoviedb.model.repository.RepositoryMovies
import io.reactivex.Single
import javax.inject.Inject

@ScopeMain
class UsecaseSavedMovies
@Inject
constructor(
    val schedulersProvider: SchedulersProvider,
    @Local val repositoryMovies: RepositoryMovies
) :
    UsecaseBase<UsecaseSavedMovies.Input, List<Movie>>(schedulersProvider) {

    override fun build(i: Input): Single<List<Movie>> {
        return repositoryMovies.saved().map { it.forEach { it.saved = true }; it }
    }

    class Input

}
