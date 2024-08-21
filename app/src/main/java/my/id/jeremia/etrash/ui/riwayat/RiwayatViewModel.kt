package my.id.jeremia.etrash.ui.riwayat

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import my.id.jeremia.etrash.data.remote.apis.data.history.response.HistorySucessResponse
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class RiwayatViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx : Context,
    val dataRepository: DataRepository
) : BaseViewModel(loader,messenger,navigator) {

    companion object {
        const val TAG = "RiwayatViewModel"
    }

    private val _historySampah = MutableStateFlow(emptyList<HistorySucessResponse.Data>())
    val historySampah = _historySampah.asStateFlow()

    init{
        updateHistorySampah()
    }

    fun updateHistorySampah() {
        launchNetwork {
            val historySampah =
                dataRepository.historySampah().first().data
                    ?: emptyList<HistorySucessResponse.Data>()
            _historySampah.emit(historySampah as List<HistorySucessResponse.Data>)
        }
    }

}