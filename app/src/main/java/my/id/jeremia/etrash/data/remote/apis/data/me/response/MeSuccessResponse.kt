package my.id.jeremia.etrash.data.remote.apis.data.me.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MeSuccessResponse(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "message")
    val message: String?, // Sukses
    @Json(name = "reason")
    val reason: Any?, // null
    @Json(name = "status")
    val status: String? // ok
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "balance")
        val balance: Int?, // 1
        @Json(name = "balanceidr")
        val balanceidr: Int?, // 1000
        @Json(name = "created_at")
        val createdAt: String?, // 2024-08-15T23:15:15.000000Z
        @Json(name = "email")
        val email: String?,
        @Json(name = "email_verified_at")
        val emailVerifiedAt: Any?, // null
        @Json(name = "id")
        val id: Int?, // 1
        @Json(name = "isAdmin")
        val isAdmin: Int?, // 1
        @Json(name = "name")
        val name: String?,
        @Json(name = "nohp")
        val nohp: String?,
        @Json(name = "photoUrl")
        val photoUrl: String?, // https://gravatar.com/avatar/581f54b15bf3afb05a1b0f75effceae1
        @Json(name = "updated_at")
        val updatedAt: String? // 2024-08-15T23:15:15.000000Z
    )
}