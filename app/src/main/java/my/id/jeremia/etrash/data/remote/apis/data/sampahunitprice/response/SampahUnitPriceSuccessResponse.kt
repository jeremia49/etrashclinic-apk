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
        val createdAt: String? = null, // 2024-08-18T23:43:41.000000Z
        @Json(name = "id")
        val id: Int? = null, // 1
        @Json(name = "imgPublicUrl")
        val imgPublicUrl: String? = null, // http://127.0.0.1:8000/storage/image/GzB52vl5xjjec8IQvm1NPBumtESGRwBYA3KyicaV.jpg
        @Json(name = "imgUrl")
        val imgUrl: String? = null, // image/GzB52vl5xjjec8IQvm1NPBumtESGRwBYA3KyicaV.jpg
        @Json(name = "rupiah")
        val rupiah: Int? = null, // 1000
        @Json(name = "satuan")
        val satuan: String? = null, // unit
        @Json(name = "title")
        val title: String? = null, // Kertas
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-08-18T23:43:41.000000Z
    )
}