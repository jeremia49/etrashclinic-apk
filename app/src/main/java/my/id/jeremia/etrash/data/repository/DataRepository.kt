package my.id.jeremia.etrash.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.id.jeremia.etrash.data.remote.apis.data.DataAPI
import my.id.jeremia.etrash.data.remote.apis.data.artikel.response.ArtikelSuccessReponse
import my.id.jeremia.etrash.data.remote.apis.data.image.response.UploadImageSucessResponse
import my.id.jeremia.etrash.data.remote.apis.data.informasi.response.InformasiSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.me.response.MeSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.produkhasil.response.ProdukHasilSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response.SampahUnitPriceSuccessResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
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

    suspend fun uploadImage(filepath: String): Flow<UploadImageSucessResponse> =
        flow {
            emit(
                dataAPI.uploadImage(
                    photo = MultipartBody.Part.createFormData(
                        name = "photo",
                        filename = filepath,
                        body = File(filepath).asRequestBody("image/*".toMediaTypeOrNull())
                    )
                )
            )
        }

}