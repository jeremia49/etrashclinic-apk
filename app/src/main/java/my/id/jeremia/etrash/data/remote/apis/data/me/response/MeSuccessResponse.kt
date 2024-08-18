package my.id.jeremia.etrash.data.remote.apis.data.me.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MeSuccessResponse(
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
        val coinBalance: Int? = null, // 0
        @Json(name = "created_at")
        val createdAt: String? = null, // 2024-08-17T20:38:21.000000Z
        @Json(name = "email")
        val email: String? = null, // jeremiaman49@gmail.com
        @Json(name = "email_verified_at")
        val emailVerifiedAt: Any? = null, // null
        @Json(name = "id")
        val id: Int? = null, // 1
        @Json(name = "isAdmin")
        val isAdmin: Int? = null, // 0
        @Json(name = "name")
        val name: String? = null, // Jeremia
        @Json(name = "nohp")
        val nohp: String? = null, //
        @Json(name = "photoUrl")
        val photoUrl: String? = null, // https://gravatar.com/avatar/6d6cb065a7fe13a052115252aadcb67c
        @Json(name = "saldoBalance")
        val saldoBalance: Int? = null, // 0
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-08-17T20:38:21.000000Z
    )
}