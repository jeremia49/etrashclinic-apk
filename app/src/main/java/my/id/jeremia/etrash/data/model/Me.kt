package my.id.jeremia.etrash.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Me(
    val balance: Int? = null, // 1
    val balanceidr: Int? = null, // 1000
    val email: String? = null,
    val id: Int? = null, // 1
    val isAdmin: Int? = null, // 1
    val name: String? = null,
    val nohp: String? = null,
    val photoUrl: String? = null, // https://gravatar.com/avatar/581f54b15bf3afb05a1b0f75effceae1
) : Parcelable
