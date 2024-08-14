package my.id.jeremia.etrash.data.remote.apis.auth.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRegisterErrorResponse(
    @Json(name = "message")
    val message: String? = null, // Validation Error!
    @Json(name = "reason")
    val reason: Reason? = null,
    @Json(name = "status")
    val status: String? = null // error
) {
    @JsonClass(generateAdapter = true)
    data class Reason(
        @Json(name = "email")
        val email: List<String?>? = null,
        @Json(name = "name")
        val name: List<String?>? = null,
        @Json(name = "nohp")
        val nohp: List<String?>? = null,
        @Json(name = "password")
        val password: List<String?>? = null
    )
}