package my.id.jeremia.etrash.data.remote.apis.auth.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRegisterRequest(
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "nohp")
    val nohp: String? = null,
    @Json(name = "password")
    val password: String? = null
)