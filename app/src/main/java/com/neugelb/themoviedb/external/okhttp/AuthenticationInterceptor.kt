package com.neugelb.themoviedb.external.okhttp

import com.neugelb.themoviedb.R
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.helper.ResourcesHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


@ScopeMain
class AuthenticationInterceptor
@Inject
constructor(val resourcesHelper: ResourcesHelper) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val httpUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_token", resourcesHelper.string(R.string.api_key))
            .build()

        val request = chain.request()
            .newBuilder()
            .url(httpUrl)
            .build()

        return chain.proceed(request)
    }
}
