package my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SampahUnitPriceSuccessResponse(
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
        val createdAt: String? = null, // 2024-08-15T10:25:24.000000Z
        @Json(name = "id")
        val id: Int? = null, // 2
        @Json(name = "imgPublicUrl")
        val imgPublicUrl: String? = null, // http://127.0.0.1:8000/storage/image/2xAferGApX0aBSuYvzBTocZe8TfNX8zeq86RnPgc.png
        @Json(name = "imgUrl")
        val imgUrl: String? = null, // image/2xAferGApX0aBSuYvzBTocZe8TfNX8zeq86RnPgc.png
        @Json(name = "maxprice")
        val maxprice: Int? = null, // 100
        @Json(name = "minprice")
        val minprice: Int? = null, // 10
        @Json(name = "satuan")
        val satuan: String? = null, // kg
        @Json(name = "title")
        val title: String? = null, // Kertas
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-08-15T10:25:24.000000Z
    )
}