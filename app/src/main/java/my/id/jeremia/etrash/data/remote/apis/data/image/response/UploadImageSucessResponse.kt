package my.id.jeremia.etrash.data.remote.apis.data.image.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadImageSucessResponse(
    @Json(name = "data")
    val `data`: String? = null, // http://127.0.0.1:8000/storage/image/l1TMjzGnogmiDPVSANrP1qH0HKKH49zlOvhXHZIn.jpg
    @Json(name = "message")
    val message: String? = null, // Sukses
    @Json(name = "reason")
    val reason: Any? = null, // null
    @Json(name = "status")
    val status: String? = null // ok
)