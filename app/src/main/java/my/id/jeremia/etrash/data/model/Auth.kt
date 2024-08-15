package my.id.jeremia.etrash.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Auth(
    val userid:String,
    val username:String,
    val email:String,
    val nohp:String,
    val accessToken:String,
    val photoUrl:String = "",
) : Parcelable
