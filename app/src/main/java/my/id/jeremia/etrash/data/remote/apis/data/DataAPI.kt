package my.id.jeremia.etrash.data.remote.apis.data

import my.id.jeremia.etrash.data.remote.RequestHeaders
import my.id.jeremia.etrash.data.remote.apis.auth.response.AuthMeSuccessResponse
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
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface DataAPI {

    @GET(Endpoint.ARTIKEL)
    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    suspend fun artikel(

    ) : ArtikelSuccessReponse

    @GET(Endpoint.INFORMASI)
    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    suspend fun informasi(

    ) : InformasiSuccessResponse

    @GET(Endpoint.SAMPAHUNITPRICE)
    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    suspend fun sampahunitprice(

    ) : SampahUnitPriceSuccessResponse

    @GET(Endpoint.PRODUKHASIL)
    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    suspend fun produkhasil(

    ) : ProdukHasilSuccessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @GET(Endpoint.ME)
    suspend fun me(

    ) : AuthMeSuccessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @Multipart
    @POST(Endpoint.UPLOADIMAGE)
    suspend fun uploadImage(
        @Part photo: MultipartBody.Part
    ) : UploadImageSucessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @POST(Endpoint.UPLOADSAMPAHPENGGUNA)
    suspend fun uploadSampahPengguna(
        @Body request: List<SubmitSampahRequestItem>
    ) : SubmitSampahSuccessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @GET(Endpoint.CAMERA)
    suspend fun scanQRCode(
        @Path("qrid") qrid: String,
        @Query("userID") userID: String
    ) : CameraSuccessResponse



    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @GET(Endpoint.HISTORYSAMPASH)
    suspend fun historySampah(

    ) : HistorySucessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @GET(Endpoint.NOTIFICATIONS)
    suspend fun notifications(

    ) : NotificationSuccessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @POST(Endpoint.SENDFCMTOKEN)
    suspend fun sendFCMToken(
        @Body request: SendFirebaseTokenRequest
    ) : SendFirebaseTokenSuccessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @GET(Endpoint.CURRENTLEADERBOARD)
    suspend fun getCurrentLeaderboard(): CurrentLeaderboardSuccessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @GET(Endpoint.LEADERBOARD)
    suspend fun getOldLeaderboard(): OldLeaderboardSuccessResponse

    @Headers(RequestHeaders.Key.AUTH_PROTECTED)
    @POST(Endpoint.UPDATEPROFILE)
    suspend fun updateProfile(
        @Body request: UpdateProfileRequest
    ): UpdateProfileSuccessResponse


}