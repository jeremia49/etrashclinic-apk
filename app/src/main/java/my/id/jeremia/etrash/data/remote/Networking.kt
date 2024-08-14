package my.id.jeremia.etrash.data.remote

import com.squareup.moshi.Moshi
import my.id.jeremia.etrash.BuildConfig
import my.id.jeremia.etrash.data.remote.interceptors.RequestHeaderInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {
    private const val NETWORK_CALL_TIMEOUT = 60


    fun<T> createService(baseUrl:String, client:OkHttpClient, service:Class<T>):T{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().build()
                )
            )
            .build()
            .create(service)
    }

    private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    fun createOkHttpClient(
        cacheDir: File,
        cacheSize: Long,
        requestHeaderInterceptor: RequestHeaderInterceptor,
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(requestHeaderInterceptor)
            .addInterceptor(getHttpLoggingInterceptor())
            .cache(Cache(cacheDir,cacheSize))
            .callTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }


    fun createOkHttpClientWithTimeout(
        requestHeaderInterceptor: RequestHeaderInterceptor,
        timeout:Long
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(requestHeaderInterceptor)
            .addInterceptor(getHttpLoggingInterceptor())
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    fun createOkHttpClientForImageDownload(
        cacheDir: File,
        cacheSize: Long,
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .cache(Cache(cacheDir,cacheSize))
            .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }

}