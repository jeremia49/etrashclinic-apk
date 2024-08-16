package my.id.jeremia.etrash.data.remote.interceptors

import kotlinx.coroutines.runBlocking
import my.id.jeremia.etrash.data.remote.RequestHeaders
import my.id.jeremia.etrash.data.repository.UserRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestHeaderInterceptor @Inject constructor(
    val userRepository: UserRepository,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val apiAuthType = request.header(RequestHeaders.Key.API_AUTH_TYPE)
        builder.removeHeader(RequestHeaders.Key.API_AUTH_TYPE)
        if(apiAuthType==RequestHeaders.Type.PROTECTED.value){
            val accessToken = userRepository.getCurrentAccessToken()
            builder.addHeader(
                RequestHeaders.Param.ACCESS_TOKEN.value,
                "Bearer $accessToken"
            )
        }

        return chain.proceed(builder.build())
    }

}