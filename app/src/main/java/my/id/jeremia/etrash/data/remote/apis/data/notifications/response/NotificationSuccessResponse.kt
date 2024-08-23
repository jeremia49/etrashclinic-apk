package my.id.jeremia.etrash.data.remote.apis.data.notifications.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationSuccessResponse(
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
        @Json(name = "content")
        val content: String? = null, // Penukaran saldo untuk WEGWER telah berhasil.
        @Json(name = "created_at")
        val createdAt: String? = null, // 2024-08-23T12:04:26.000000Z
        @Json(name = "id")
        val id: Int? = null, // 13
        @Json(name = "isRead")
        val isRead: Int? = null, // 1
        @Json(name = "title")
        val title: String? = null, // Penukaran Saldo
        @Json(name = "type")
        val type: String? = null, // MONEY
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-08-23T12:04:26.000000Z
    )
}