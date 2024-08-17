package my.id.jeremia.etrash.ui.homepage

import android.content.Context
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import my.id.jeremia.etrash.data.model.Article
import my.id.jeremia.etrash.data.model.Informasi
import my.id.jeremia.etrash.data.model.Me
import my.id.jeremia.etrash.data.model.ProdukHasil
import my.id.jeremia.etrash.data.remote.apis.data.artikel.response.ArtikelSuccessReponse
import my.id.jeremia.etrash.data.remote.apis.data.informasi.response.InformasiSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.produkhasil.response.ProdukHasilSuccessResponse
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.data.repository.UserRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx: Context,
    val userRepository: UserRepository,
    val dataRepository: DataRepository
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "HomepageViewModel"
    }

    private val _namapengguna = MutableStateFlow("")
    private val _photoUrl = MutableStateFlow("")
    private val _artikels = MutableStateFlow(emptyList<Article>())
    private val _informasis = MutableStateFlow(emptyList<Informasi>())
    private val _produkhasils = MutableStateFlow(emptyList<ProdukHasil>())
    private val _me = MutableStateFlow(Me())

    val namapengguna = _namapengguna.asStateFlow()
    val photoUrl = _photoUrl.asStateFlow()
    val artikels = _artikels.asStateFlow()
    val informasis = _informasis.asStateFlow()
    val produkhasils = _produkhasils.asStateFlow()
    val me = _me.asStateFlow()

    init {
        userRepository.getCurrentAuth()?.let {
            _namapengguna.value = it.username
            _photoUrl.value = it.photoUrl
        }

        updateMe()
        updateInformasi()
        updateArtikel()
        updateProdukHasil()
    }

    fun updateMe() {
        launchNetwork {
            val meResponse = dataRepository.getMe().first().data
            Log.d(TAG, meResponse.toString())
            val mee = Me(
                balanceidr = meResponse!!.balanceidr,
                balance = meResponse.balance,
            )
            _me.emit(mee)
        }
    }

    fun updateArtikel() {
        launchNetwork {
            val artikelResponse =
                dataRepository.getArtikel().first().data ?: emptyList<ArtikelSuccessReponse.Data>()
            val artikel = artikelResponse.map {
                Article(
                    author = it!!.author,
                    createdAt = it.createdAt,
                    imgPublicUrl = it.imgPublicUrl,
                    isVideo = it.isVideo,
                    publicUrl = it.publicUrl,
                    title = it.title,
                )
            }
            _artikels.emit(artikel)
        }
    }

    fun updateInformasi() {
        launchNetwork {
            val informasiResponse = dataRepository.getInformasi().first().data
                ?: emptyList<InformasiSuccessResponse.Data>()
            val informasi = informasiResponse.map {
                Informasi(
                    createdAt = it!!.createdAt,
                    imgPublicUrl = it.imgPublicUrl,
                    publicUrl = it.publicUrl,
                    title = it.title,
                )
            }
            _informasis.emit(informasi)
        }
    }

    fun updateProdukHasil() {
        launchNetwork {
            val produkHasilResponse = dataRepository.getProdukHasil().first().data
                ?: emptyList<ProdukHasilSuccessResponse.Data>()
            val produkHasil = produkHasilResponse.map {
                ProdukHasil(
                    imgPublicUrl = it!!.imgPublicUrl,
                    title = it.title,
                    price = it.price
                )
            }
            _produkhasils.emit(produkHasil)
        }
    }


}

