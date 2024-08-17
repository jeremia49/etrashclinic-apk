package my.id.jeremia.etrash.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProdukHasil(
    val id: Int? = null, // 2
    val imgPublicUrl: String? = null, // http://127.0.0.1:8000/storage/image/Z57afzbVlIzR8EsmaPNhgjmyL2pER0FWZEQtOTdM.png
    val price: Int? = null, // 16136
    val title: String? = null, // Tas Bagus
):Parcelable
