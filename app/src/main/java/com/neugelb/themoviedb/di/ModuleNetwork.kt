package com.neugelb.themoviedb.di

import android.content.Context
import com.google.gson.Gson
import com.neugelb.themoviedb.Constants
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.external.okhttp.AuthenticationInterceptor
import com.neugelb.themoviedb.external.okhttp.CachingInterceptor
import com.neugelb.themoviedb.external.okhttp.LoggingInterceptor
import com.neugelb.themoviedb.external.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class ModuleNetwork {

    @ScopeMain
    @Provides
    fun cache(context: Context): Cache {
        return Cache(
            File(context.cacheDir, Constants.Cache.HTTP_DIR),
            Constants.Cache.HTTP_SIZE
        )
    }

    @ScopeMain
    @Provides
    fun okHttpClient(
        cache: Cache,
        loggingInterceptor: LoggingInterceptor,
        cachingInterceptor: CachingInterceptor,
        authenticationInterceptor: AuthenticationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(cachingInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authenticationInterceptor)
            .readTimeout(
                Constants.API.CONNECTION_TIMEOUT_SECONDS,
                TimeUnit.SECONDS
            )
            .writeTimeout(
                Constants.API.CONNECTION_TIMEOUT_SECONDS,
                TimeUnit.SECONDS
            )
            .connectTimeout(
                Constants.API.CONNECTION_TIMEOUT_SECONDS,
                TimeUnit.SECONDS
            )
            .build()
    }

    @ScopeMain
    @Provides
    fun picasso(context: Context, downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context)
            .downloader(downloader)
            .build()
    }

    @ScopeMain
    @Provides
    fun okHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }

    @ScopeMain
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        callFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.API.BASE_API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(callFactory)
            .addConverterFactory(converterFactory)
            .build()
    }

    @ScopeMain
    @Provides
    fun callFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @ScopeMain
    @Provides
    fun converterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

}