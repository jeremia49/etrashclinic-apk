package my.id.jeremia.etrash.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val loader: Loader,
    val messenger: Messenger,
    val navigator: Navigator,
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "SplashViewModel"
    }

    init {
//        viewModelScope.launch {
//            val user = userRepository.getCurrentAuth()
//            if (user === null) {
//                navigator.navigateTo(Destination.Login.route, true)
//            }
//
//            if (user !== null) {
//                launchNetwork(error = {
//                    try {
//                        val error = Moshi.Builder().build().adapter(AuthErrorResponse::class.java)
//                            .fromJson(it.fullResponse)
//
//                        if ((error!!.status!! == "error") and (error.message!! == "Unauthenticated.")) {
//                            runBlocking {
//                                userRepository.removeCurrentUser();
//                            }
//                            navigator.navigateTo(Destination.Login.route, true)
//                        }else{
//                            navigator.navigateTo(Destination.Home.Find.route, true)
//                        }
//                    } catch (e: Exception) {
//                        Log.e(TAG, e.message.toString())
//                        navigator.navigateTo(Destination.Home.Find.route, true)
//                    }
//                },
//                    finish = {
//                        viewModelScope.launch {
//                            if (userRepository.getCurrentAuth() !== null) {
//                                navigator.navigateTo(Destination.Home.Find.route, true)
//                            }
//                        }
//                    }) {
//                    flow {
//                        emit(quickAuthAPI.me())
//                    }.collect {
//                        navigator.navigateTo(Destination.Home.Find.route, true)
//                    }
//                }
//            }
//
//        }

    }
}