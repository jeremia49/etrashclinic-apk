package my.id.jeremia.etrash.data.remote.apis.data.updateprofile.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateProfileRequest(
    @Json(name = "name")
    val name: String? = null, // Agung123123213
    @Json(name = "nohp")
    val nohp: String? = null, // nohp
    @Json(name = "password")
    val password: String? = null, // halodunia
    @Json(name = "photoUrl")
    val photoUrl: String? = null // https://google.com
)