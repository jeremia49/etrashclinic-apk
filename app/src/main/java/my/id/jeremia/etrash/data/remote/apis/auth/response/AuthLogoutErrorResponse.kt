package my.id.jeremia.etrash.data.remote.apis.auth.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthLogoutErrorResponse(
    @Json(name = "message")
    val message: String?,
    @Json(name = "reason")
    val reason: Any?,
    @Json(name = "status")
    val status: String?
)