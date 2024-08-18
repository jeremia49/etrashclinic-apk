package my.id.jeremia.etrash.data.remote.apis.data

import my.id.jeremia.etrash.data.remote.RequestHeaders
import my.id.jeremia.etrash.data.remote.apis.data.artikel.response.ArtikelSuccessReponse
import my.id.jeremia.etrash.data.remote.apis.data.image.response.UploadImageSucessResponse
import my.id.jeremia.etrash.data.remote.apis.data.informasi.response.InformasiSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.me.response.MeSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.produkhasil.response.ProdukHasilSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response.SampahUnitPriceSuccessResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DataAPI {

    @GET(Endpoint.ARTIKEL)
    suspend fun artikel(

    ) : ArtikelSuccessReponse

    @GET(Endpoint.INFORMASI)
    @Headers(RequestHeaders.Key.AUTH_PUBLIC)
    suspend fun informasi(

    ) : InformasiSuccessResponse

    @GET(Endpoint.SAMPAHUNITPRICE)
    @Headers(RequestHeaders.Key.AUTH_PUBLIC)
    suspend fun sampahunitprice(

    ) : SampahUnitPriceSuccessResponse

    @GET(Endpoint.PRODUKHASIL)
    @Headers(RequestHeaders.Key.AUTH_PUBLIC)
    suspend fun produkhasil(

    ) : ProdukHasilSuccessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @GET(Endpoint.ME)
    suspend fun me(

    ) : MeSuccessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @Multipart
    @POST(Endpoint.UPLOADIMAGE)
    suspend fun uploadImage(
        @Part photo: MultipartBody.Part
    ) : UploadImageSucessResponse
}