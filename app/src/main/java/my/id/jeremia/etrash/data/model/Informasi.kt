package my.id.jeremia.etrash.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Informasi(
    val createdAt: String? = null, // 2024-08-15T10:56:48.000000Z
    val id: Int? = null, // 1
    val imgPublicUrl: String? = null, // http://localhost:8000/storage/image/LgqT4pOK8dQJEhOik0uweh5AHkNsrq8yQpVoziU5.png
    val publicUrl: String? = null, // http://localhost:8000/viewinformasi/1
    val title: String? = null, // apapun jadi bro
):Parcelable
