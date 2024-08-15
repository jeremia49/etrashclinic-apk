package my.id.jeremia.etrash.data.remote.apis.data.artikel.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtikelSuccessReponse(
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
        val createdAt: String? = null, // 2024-08-15T11:00:24.000000Z
        @Json(name = "id")
        val id: Int? = null, // 1
        @Json(name = "imgPublicUrl")
        val imgPublicUrl: String? = null, // http://127.0.0.1:8000/storage/image/TjIPz1X34sYD0cyjPHYFw0G4V8eSuJnxsO8vlqo0.png
        @Json(name = "imgUrl")
        val imgUrl: String? = null, // image/TjIPz1X34sYD0cyjPHYFw0G4V8eSuJnxsO8vlqo0.png
        @Json(name = "isVideo")
        val isVideo: Int? = null, // 0
        @Json(name = "publicUrl")
        val publicUrl: String? = null, // http://127.0.0.1:8000/viewartikel/1
        @Json(name = "title")
        val title: String? = null, // aethaerthjartjary
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-08-15T11:00:24.000000Z
    )
}