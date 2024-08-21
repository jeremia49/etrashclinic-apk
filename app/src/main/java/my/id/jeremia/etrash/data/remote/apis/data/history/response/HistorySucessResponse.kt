package my.id.jeremia.etrash.data.remote.apis.data.history.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HistorySucessResponse(
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
        val createdAt: String? = null, // 2024-08-21T01:11:35.000000Z
        @Json(name = "id")
        val id: Int? = null, // 1
        @Json(name = "imgUrl")
        val imgUrl: String? = null, // http://ccee-38-9-128-236.ngrok-free.app/storage/image/SqFSGNczBM61Xpr2ccIat8ewN1qY7Csd10kXcBoq.jpg
        @Json(name = "isApproved")
        val isApproved: Int? = null, // 0
        @Json(name = "isDeclined")
        val isDeclined: Int? = null, // 0
        @Json(name = "origprice")
        val origprice: Int? = null, // 2000
        @Json(name = "origstatus")
        val origstatus: String? = null, // kg
        @Json(name = "price")
        val price: Any? = null, // null
        @Json(name = "satuan")
        val satuan: Any? = null, // null
        @Json(name = "title")
        val title: String? = null, // Buku Bersampul
        @Json(name = "total")
        val total: Int? = null, // 2
        @Json(name = "unitid")
        val unitid: Int? = null, // 3
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-08-21T01:11:35.000000Z
    )
}