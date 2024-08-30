package my.id.jeremia.etrash.data.remote.apis.data.fcmtoken.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendFirebaseTokenRequest(
    @Json(name = "fcmToken")
    val fcmToken: String? = null // abc
)