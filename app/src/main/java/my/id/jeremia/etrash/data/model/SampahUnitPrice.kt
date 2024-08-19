package my.id.jeremia.etrash.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SampahUnitPrice (
    val id: Int? = null,
    val imgPublicUrl: String? = null, // http://127.0.0.1:8000/storage/image/2xAferGApX0aBSuYvzBTocZe8TfNX8zeq86RnPgc.png
    val rupiahPrice:Int?=null, // 1000
    val satuan: String? = null, // kg
    val title: String? = null, // Kertas
):Parcelable