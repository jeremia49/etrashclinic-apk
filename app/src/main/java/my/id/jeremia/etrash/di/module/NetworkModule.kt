package my.id.jeremia.etrash.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.id.jeremia.etrash.BuildConfig
import my.id.jeremia.etrash.data.remote.Networking
import my.id.jeremia.etrash.data.remote.apis.auth.AuthAPI
import my.id.jeremia.etrash.data.remote.apis.data.DataAPI
import my.id.jeremia.etrash.data.remote.interceptors.RequestHeaderInterceptor
import my.id.jeremia.etrash.di.qualifier.BaseUrl
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiOkHttpClient(
        @ApplicationContext context: Context,
        requestHeaderInterceptor: RequestHeaderInterceptor,
    ): OkHttpClient = Networking.createOkHttpClient(
        context.cacheDir,
        100 * 1024 * 1024,
        requestHeaderInterceptor,
    )


    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideAuthAPI(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient
    ): AuthAPI = Networking.createService(
        baseUrl,
        okHttpClient,
        AuthAPI::class.java
    )

    @Provides
    @Singleton
    fun provideDataAPI(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient
    ): DataAPI = Networking.createService(
        baseUrl,
        okHttpClient,
        DataAPI::class.java
    )


}