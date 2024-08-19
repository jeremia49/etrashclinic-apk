package my.id.jeremia.etrash.data.model

data class Sampah(
    val id:Int,
    val title:String,
    val berat:Int,
    val pictureUrl:String,
    val rupiahPrice:Int,
    val satuan:String,
    val uploadedPictureUrl:String?=null,
)
