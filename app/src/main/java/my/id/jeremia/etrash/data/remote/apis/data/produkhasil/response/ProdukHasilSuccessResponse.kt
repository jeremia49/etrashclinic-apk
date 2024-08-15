package my.id.jeremia.etrash.data.remote.apis.data.produkhasil.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProdukHasilSuccessResponse(
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
        val createdAt: String? = null, // 2024-08-15T10:36:00.000000Z
        @Json(name = "id")
        val id: Int? = null, // 2
        @Json(name = "imgPublicUrl")
        val imgPublicUrl: String? = null, // http://127.0.0.1:8000/storage/image/Z57afzbVlIzR8EsmaPNhgjmyL2pER0FWZEQtOTdM.png
        @Json(name = "imgUrl")
        val imgUrl: String? = null, // image/Z57afzbVlIzR8EsmaPNhgjmyL2pER0FWZEQtOTdM.png
        @Json(name = "price")
        val price: Int? = null, // 16136
        @Json(name = "title")
        val title: String? = null, // Tas Bagus
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-08-15T10:36:00.000000Z
    )
}