package my.id.jeremia.etrash.data.remote.apis.data

import my.id.jeremia.etrash.data.remote.apis.auth.request.AuthLoginRequest
import my.id.jeremia.etrash.data.remote.apis.auth.response.AuthLoginSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.artikel.response.ArtikelSuccessReponse
import my.id.jeremia.etrash.data.remote.apis.data.informasi.response.InformasiSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.produkhasil.response.ProdukHasilSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response.SampahUnitPriceSuccessResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface DataAPI {

    @GET(Endpoint.ARTIKEL)
    suspend fun artikel(

    ) : ArtikelSuccessReponse

    @GET(Endpoint.INFORMASI)
    suspend fun informasi(

    ) : InformasiSuccessResponse

    @GET(Endpoint.SAMPAHUNITPRICE)
    suspend fun sampahunitprice(

    ) : SampahUnitPriceSuccessResponse

    @GET(Endpoint.PRODUKHASIL)
    suspend fun produkhasil(

    ) : ProdukHasilSuccessResponse



}