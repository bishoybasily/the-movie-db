package com.neugelb.themoviedb.external.okhttp

import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.helper.NetworkHelper
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


@ScopeMain
class CachingInterceptor
@Inject
constructor(private val networkHelper: NetworkHelper) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .cacheControl(if (networkHelper.isInternetAvailable) CacheControl.FORCE_NETWORK else CacheControl.FORCE_CACHE)
            .build()

        return chain.proceed(request)

    }
}
