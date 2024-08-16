package my.id.jeremia.etrash.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article (
    val author: Int? = null, // 1
    val createdAt: String? = null, // 2024-08-15T11:00:24.000000Z
    val imgPublicUrl: String? = null, // http://127.0.0.1:8000/storage/image/TjIPz1X34sYD0cyjPHYFw0G4V8eSuJnxsO8vlqo0.png
    val isVideo: Int? = null, // 0
    val publicUrl: String? = null, // http://127.0.0.1:8000/viewartikel/1
    val title: String? = null, // aethaerthjartjary
):Parcelable