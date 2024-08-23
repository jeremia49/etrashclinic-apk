package my.id.jeremia.etrash.data.remote.apis.auth.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthMeSuccessResponse(
    @Json(name = "data")
    val `data`: Data? = null,
    @Json(name = "message")
    val message: String? = null, // Sukses
    @Json(name = "reason")
    val reason: Any? = null, // null
    @Json(name = "status")
    val status: String? = null // ok
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "coinBalance")
        val coinBalance: Int? = null,
        @Json(name = "created_at")
        val createdAt: String? = null,
        @Json(name = "email")
        val email: String? = null,
        @Json(name = "email_verified_at")
        val emailVerifiedAt: Any? = null, // null
        @Json(name = "fcmLastUpdate")
        val fcmLastUpdate: Any? = null, // null
        @Json(name = "fcmToken")
        val fcmToken: Any? = null, // null
        @Json(name = "id")
        val id: Int? = null, // 1
        @Json(name = "isAdmin")
        val isAdmin: Int? = null, // 1
        @Json(name = "isNotificationExist")
        val isNotificationExist: Boolean? = null, // false
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "nohp")
        val nohp: String? = null,
        @Json(name = "photoUrl")
        val photoUrl: String? = null, // https://gravatar.com/avatar/581f54b15bf3afb05a1b0f75effceae1
        @Json(name = "saldoBalance")
        val saldoBalance: Int? = null, // 600
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-08-23T12:04:26.000000Z
    )
}