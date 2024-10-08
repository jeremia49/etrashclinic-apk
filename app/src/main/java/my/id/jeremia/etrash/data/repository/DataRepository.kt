package my.id.jeremia.etrash.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.id.jeremia.etrash.data.remote.apis.auth.response.AuthMeSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.DataAPI
import my.id.jeremia.etrash.data.remote.apis.data.artikel.response.ArtikelSuccessReponse
import my.id.jeremia.etrash.data.remote.apis.data.camera.CameraSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.fcmtoken.request.SendFirebaseTokenRequest
import my.id.jeremia.etrash.data.remote.apis.data.fcmtoken.response.SendFirebaseTokenSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.history.response.HistorySucessResponse
import my.id.jeremia.etrash.data.remote.apis.data.image.response.UploadImageSucessResponse
import my.id.jeremia.etrash.data.remote.apis.data.informasi.response.InformasiSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.leaderboard.response.CurrentLeaderboardSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.leaderboard.response.OldLeaderboardSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.notifications.response.NotificationSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.produkhasil.response.ProdukHasilSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response.SampahUnitPriceSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.submitsampah.request.SubmitSampahRequestItem
import my.id.jeremia.etrash.data.remote.apis.data.submitsampah.response.SubmitSampahSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.updateprofile.request.UpdateProfileRequest
import my.id.jeremia.etrash.data.remote.apis.data.updateprofile.response.UpdateProfileSuccessResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val dataAPI: DataAPI
) {
    suspend fun getArtikel(): Flow<ArtikelSuccessReponse> =
        flow {
            emit(dataAPI.artikel())
        }

    suspend fun getInformasi(): Flow<InformasiSuccessResponse> =
        flow {
            emit(dataAPI.informasi())
        }

    suspend fun getSampahUnitPrice(): Flow<SampahUnitPriceSuccessResponse> =
        flow {
            emit(dataAPI.sampahunitprice())
        }

    suspend fun getProdukHasil(): Flow<ProdukHasilSuccessResponse> =
        flow {
            emit(dataAPI.produkhasil())
        }

    suspend fun getMe(): Flow<AuthMeSuccessResponse> =
        flow {
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

    suspend fun uploadSampah(data: List<SubmitSampahRequestItem>): Flow<SubmitSampahSuccessResponse> =
        flow {
            emit(
                dataAPI.uploadSampahPengguna(data)
            )
        }

    suspend fun checkQRCode(qrid: String, userID: String): Flow<CameraSuccessResponse> =
        flow {
            emit(
                dataAPI.scanQRCode(qrid, userID)
            )
        }
    suspend fun historySampah(): Flow<HistorySucessResponse> =
        flow {
            emit(
                dataAPI.historySampah()
            )
        }

    suspend fun notifications() : Flow<NotificationSuccessResponse> =
        flow{
            emit(
                dataAPI.notifications()
            )
        }

    fun sendFCMToken(data: String): Flow<SendFirebaseTokenSuccessResponse> =
        flow {
            emit(dataAPI.sendFCMToken(SendFirebaseTokenRequest(data)))
        }

    suspend fun currentLeaderboard() : Flow<CurrentLeaderboardSuccessResponse> =
        flow{
            emit(
                dataAPI.getCurrentLeaderboard()
            )
        }
    suspend fun pastLeaderboard() : Flow<OldLeaderboardSuccessResponse> =
        flow{
            emit(
                dataAPI.getOldLeaderboard()
            )
        }


    suspend fun updateProfile(data: UpdateProfileRequest): Flow<UpdateProfileSuccessResponse> =
        flow {
            emit(
                dataAPI.updateProfile(data)
            )
        }

}