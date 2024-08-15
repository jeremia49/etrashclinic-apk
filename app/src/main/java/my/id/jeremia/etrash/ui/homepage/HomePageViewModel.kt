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

    val namapengguna = _namapengguna.asStateFlow()
    val photoUrl = _photoUrl.asStateFlow()

    init {
        userRepository.getCurrentAuth()?.let {
            _namapengguna.value = it.username
            _photoUrl.value = it.photoUrl
        }

        Log.d(TAG, "Testing")


        viewModelScope.launch{

            val z = dataRepository.getArtikel().first()
            Log.d(TAG, "Data : ${z}")

        }



    }

}

