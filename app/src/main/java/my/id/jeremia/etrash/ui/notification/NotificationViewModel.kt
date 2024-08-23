package my.id.jeremia.etrash.ui.notification

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import my.id.jeremia.etrash.data.remote.apis.data.notifications.response.NotificationSuccessResponse
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx : Context,
    val dataRepository: DataRepository,
) : BaseViewModel(loader,messenger,navigator) {

    companion object {
        const val TAG = "NotificationViewModel"
    }

    private val _notifications = MutableStateFlow(emptyList<NotificationSuccessResponse.Data>())
    val notifications = _notifications.asStateFlow()

    init{
        updateNotifications()
    }

    fun updateNotifications() {
        launchNetwork {
            val notification =
                dataRepository.notifications().first().data
                    ?: emptyList<NotificationSuccessResponse.Data>()
            _notifications.emit(notification as List<NotificationSuccessResponse.Data>)
        }
    }

}