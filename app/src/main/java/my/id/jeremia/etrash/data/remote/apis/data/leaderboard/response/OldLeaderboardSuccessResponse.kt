package my.id.jeremia.etrash.data.remote.apis.data.leaderboard.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OldLeaderboardSuccessResponse(
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
        @Json(name = "authorID")
        val authorID: Int? = null, // 1
        @Json(name = "bulan")
        val bulan: Int? = null, // 9
        @Json(name = "created_at")
        val createdAt: String? = null, // 2024-09-05T17:19:04.000000Z
        @Json(name = "id")
        val id: Int? = null, // 4
        @Json(name = "league")
        val league: String? = null, // diamond
        @Json(name = "name")
        val name: String? = null, // Jeremia Manurung
        @Json(name = "photoUrl")
        val photoUrl: String? = null, // https://gravatar.com/avatar/581f54b15bf3afb05a1b0f75effceae1
        @Json(name = "rank")
        val rank: Int? = null, // 2
        @Json(name = "tahun")
        val tahun: Int? = null, // 2024
        @Json(name = "totalSampah")
        val totalSampah: Int? = null, // 101
        @Json(name = "updated_at")
        val updatedAt: String? = null // 2024-09-05T17:19:04.000000Z
    )
}