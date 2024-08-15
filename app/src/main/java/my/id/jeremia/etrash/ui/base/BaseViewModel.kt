package my.id.jeremia.etrash.ui.base

import Message
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.data.remote.response.ApiErrorResponse
import my.id.jeremia.etrash.data.remote.utils.toApiErrorResponse
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Navigator
import kotlin.coroutines.cancellation.CancellationException


abstract class BaseViewModel(
    private val loader: Loader,
    private val messenger: Messenger,
    private val navigator: Navigator
) : ViewModel() {

    companion object {
        const val TAG = "BaseViewModel"
    }

    protected fun launchNetwork(
        silent: Boolean = false,
        error: (ApiErrorResponse) -> Unit = {},
        finish:()->Unit={},
        block: suspend CoroutineScope.() -> Unit
    ) {
        if (!silent) {
            loader.start()
            viewModelScope.launch {
                try {
                    block()
                } catch (e: Throwable) {
                    if (e is CancellationException) return@launch
                    val errorResponse = e.toApiErrorResponse()
                    handleNetworkError(errorResponse)
                    error(errorResponse)
                    e.message?.let { Log.e(TAG, it) }
                } finally {
                    loader.stop()
                    finish();
                }
            }
        } else {
            viewModelScope.launch {
                try {
                    block()
                } catch (e: Throwable) {
                    if (e is CancellationException) return@launch
                    val errorResponse = e.toApiErrorResponse()
                    handleNetworkError(errorResponse)
                    error(errorResponse)
                    e.message?.let { Log.e(TAG, it) }
                } finally {
                    finish();
                }
            }
        }
    }

    private fun handleNetworkError(err: ApiErrorResponse) {
        when (err.status) {
            ApiErrorResponse.Status.HTTP_BAD_GATEWAY,
            ApiErrorResponse.Status.REMOTE_CONNECTION_ERROR -> {
                messenger.deliverRes(Message.error(R.string.server_connection_error))
//                navigator.navigateTo(Destination.ServerUnreachable.route)
            }

            ApiErrorResponse.Status.NETWORK_CONNECTION_ERROR ->
                messenger.deliverRes(Message.error(R.string.no_internet_connection))

//            ApiErrorResponse.Status.HTTP_INTERNAL_ERROR ->
//                messenger.deliverRes(Message.error(R.string.network_internal_error))
//
//            ApiErrorResponse.Status.HTTP_UNAVAILABLE -> {
//                messenger.deliverRes(Message.error(R.string.network_server_not_available))
//                navigator.navigateTo(Destination.ServerUnreachable.route)
//            }

            ApiErrorResponse.Status.UNKNOWN ->
                messenger.deliverRes(Message.error(R.string.something_went_wrong))

            ApiErrorResponse.Status.API_ERROR -> {

            }

        }
    }

}