package com.neugelb.themoviedb.model.repository

import com.neugelb.themoviedb.external.dagger.ScopeMain
import retrofit2.Retrofit
import javax.inject.Inject

@ScopeMain
class RepositoryMovie
@Inject
constructor(val retrofit: Retrofit)
