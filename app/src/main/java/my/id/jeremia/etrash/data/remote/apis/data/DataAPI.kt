package my.id.jeremia.etrash.data.remote.apis.data

import my.id.jeremia.etrash.data.remote.RequestHeaders
import my.id.jeremia.etrash.data.remote.apis.auth.request.AuthLoginRequest
import my.id.jeremia.etrash.data.remote.apis.auth.response.AuthLoginSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.artikel.response.ArtikelSuccessReponse
import my.id.jeremia.etrash.data.remote.apis.data.informasi.response.InformasiSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.me.response.MeSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.produkhasil.response.ProdukHasilSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response.SampahUnitPriceSuccessResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

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

}