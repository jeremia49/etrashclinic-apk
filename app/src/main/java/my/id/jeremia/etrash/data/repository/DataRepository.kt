package my.id.jeremia.etrash.data.repository

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.id.jeremia.etrash.data.model.Article
import my.id.jeremia.etrash.data.remote.apis.auth.request.AuthLoginRequest
import my.id.jeremia.etrash.data.remote.apis.auth.response.AuthLoginSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.DataAPI
import my.id.jeremia.etrash.data.remote.apis.data.artikel.response.ArtikelSuccessReponse
import my.id.jeremia.etrash.data.remote.apis.data.informasi.response.InformasiSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.me.response.MeSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.produkhasil.response.ProdukHasilSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response.SampahUnitPriceSuccessResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val dataAPI: DataAPI
){
    suspend fun getArtikel(): Flow<ArtikelSuccessReponse> =
        flow {
            emit(dataAPI.artikel())
        }

    suspend fun getInformasi(): Flow<InformasiSuccessResponse> =
        flow{
            emit(dataAPI.informasi())
        }

    suspend fun getSampahUnitPrice(): Flow<SampahUnitPriceSuccessResponse> =
        flow{
            emit(dataAPI.sampahunitprice())
        }

    suspend fun getProdukHasil(): Flow <ProdukHasilSuccessResponse> =
        flow{
            emit(dataAPI.produkhasil())
        }

    suspend fun getMe(): Flow <MeSuccessResponse> =
        flow{
            emit(dataAPI.me())
        }


}