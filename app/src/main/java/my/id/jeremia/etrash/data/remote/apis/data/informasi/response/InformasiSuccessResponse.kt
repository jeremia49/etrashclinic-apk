package my.id.jeremia.etrash.data.remote.apis.data.informasi.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InformasiSuccessResponse(
    @Json(name = "data")
    val `data`: List<Data?>? = null,
    @Json(name = "message")
    val message: String? = null, // Sukses
    @Json(name = "reason")
    val reason: Any? = null, // null
    @Json(name = "status")
    val status: String? = null // ok
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "author")
        val author: Int? = null, // 1
        @Json(name = "created_at")
        val createdAt: String? = null, // 2024-08-15T10:56:48.000000Z
        @Json(name = "id")
        val id: Int? = null, // 1
        @Json(name = "imgPublicUrl")
        val imgPublicUrl: String? = null, // http://localhost:8000/storage/image/LgqT4pOK8dQJEhOik0uweh5AHkNsrq8yQpVoziU5.png
        @Json(name = "imgUrl")
        val imgUrl: String? = null, // image/LgqT4pOK8dQJEhOik0uweh5AHkNsrq8yQpVoziU5.png
        @Json(name = "publicUrl")
        val publicUrl: String? = null, // http://localhost:8000/viewinformasi/1
        @Json(name = "title")
        val title: String? = null, // apapun jadi bro
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-08-15T10:56:48.000000Z
    )
}