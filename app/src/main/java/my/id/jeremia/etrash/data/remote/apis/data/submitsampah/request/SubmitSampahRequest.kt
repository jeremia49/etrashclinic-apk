package my.id.jeremia.etrash.data.remote.apis.data.submitsampah.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubmitSampahRequestItem(
    @Json(name = "berat")
    val berat: String? = null, // 1
    @Json(name = "id")
    val id: String? = null, // 1
    @Json(name = "image")
    val image: String? = null, // https://google.com
    @Json(name = "satuan")
    val satuan: String? = null // kg
)