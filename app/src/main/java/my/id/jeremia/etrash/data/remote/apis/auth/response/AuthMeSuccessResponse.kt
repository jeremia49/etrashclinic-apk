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
        val coinBalance: Int? = null, // 33
        @Json(name = "created_at")
        val createdAt: String? = null, // 2024-08-21T01:04:48.000000Z
        @Json(name = "email")
        val email: String? = null,
        @Json(name = "email_verified_at")
        val emailVerifiedAt: Any? = null, // null
        @Json(name = "id")
        val id: Int? = null, // 1
        @Json(name = "isAdmin")
        val isAdmin: Int? = null, // 1
        @Json(name = "isNotificationExist")
        val isNotificationExist: Boolean? = null, // true
        @Json(name = "leagueBulanIni")
        val leagueBulanIni: String? = null, // diamond
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "nohp")
        val nohp: String? = null,
        @Json(name = "photoUrl")
        val photoUrl: String? = null, // https://gravatar.com/avatar/581f54b15bf3afb05a1b0f75effceae1
        @Json(name = "saldoBalance")
        val saldoBalance: Int? = null, // 1988
        @Json(name = "totalSampahBulanIni")
        val totalSampahBulanIni: Int? = null, // 101
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-09-05T17:18:50.000000Z
    )
}