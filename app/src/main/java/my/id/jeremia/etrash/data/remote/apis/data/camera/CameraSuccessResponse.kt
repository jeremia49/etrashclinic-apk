package my.id.jeremia.etrash.data.remote.apis.data.camera


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CameraSuccessResponse(
    @Json(name = "message")
    val message: String? = null, // Halo Dunia !
    @Json(name = "reason")
    val reason: Any? = null, // null
    @Json(name = "status")
    val status: String? = null // ok
)