package my.id.jeremia.etrash.ui.homepage

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import my.id.jeremia.etrash.data.model.Article
import my.id.jeremia.etrash.data.remote.apis.data.artikel.response.ArtikelSuccessReponse
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

    val namapengguna = _namapengguna.asStateFlow()
    val photoUrl = _photoUrl.asStateFlow()
    val artikels = _artikels.asStateFlow()

    init {
        userRepository.getCurrentAuth()?.let {
            _namapengguna.value = it.username
            _photoUrl.value = it.photoUrl
        }

        updateArtikel()
    }

    fun updateArtikel(){
        launchNetwork {
            val artikelResponse = dataRepository.getArtikel().first().data ?: emptyList<ArtikelSuccessReponse.Data>()
            val artikel = artikelResponse.map{
                Article(
                        author = it!!.author,
                        createdAt = it.createdAt,
                        imgPublicUrl = it.imgPublicUrl,
                        isVideo = it.isVideo,
                        publicUrl = it.publicUrl,
              )
            }
            _artikels.emit(artikel)
        }
    }

}

