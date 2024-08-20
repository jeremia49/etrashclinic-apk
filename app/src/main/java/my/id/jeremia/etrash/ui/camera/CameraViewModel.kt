package my.id.jeremia.etrash.ui.camera

import android.content.Context
import android.net.Uri
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.data.repository.UserRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx: Context,
    val dataRepository: DataRepository,
    val userRepository: UserRepository,
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "CameraViewModel"
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private lateinit var userid: String

    private var lastScannedMilis = 0L

    init {
        val auth = userRepository.getCurrentAuth()
        userid = auth!!.userid
    }


    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun checkQRCode(qrid: String, userID: String) {
        launchNetwork(
            finish = {
                setLoading(false);
            }
        ) {
            setLoading(true);
            val response = dataRepository.checkQRCode(qrid, userID).first()
            messenger.deliver(Message.info(response.message!!))
            navigator.navigateTo(Destination.Home.UploadSampah.route, true)
        }
    }

    fun onScannedAction(result: String) {
        if(System.currentTimeMillis() - lastScannedMilis > 2000){
            lastScannedMilis = System.currentTimeMillis()
            if(!isLoading.value){
                val qrid = result.substringAfterLast("/")
                if (qrid != "") {
                    checkQRCode(qrid, userid)
                }
            }
        }

    }

}