package my.id.jeremia.etrash.data.repository

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.id.jeremia.etrash.data.model.Article
import my.id.jeremia.etrash.data.remote.apis.auth.request.AuthLoginRequest
import my.id.jeremia.etrash.data.remote.apis.auth.response.AuthLoginSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.DataAPI
import my.id.jeremia.etrash.data.remote.apis.data.artikel.response.ArtikelSuccessReponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val dataAPI: DataAPI
){

//    fun getArtikel(): Single<List<Article>> {
//        return Single.create{event->
//            event.onSuccess(
//                dataAPI.artikel().data?.map{
//                    Article(
//                        author = it!!.author,
//                        createdAt = it.createdAt,
//                        imgPublicUrl = it.imgPublicUrl,
//                        isVideo = it.isVideo,
//                        publicUrl = it.publicUrl,
//                    )
//                }?: emptyList()
//            )
//
//        }
//    }

    suspend fun getArtikel(): Flow<ArtikelSuccessReponse> =
        flow {
            emit(dataAPI.artikel())
        }


}