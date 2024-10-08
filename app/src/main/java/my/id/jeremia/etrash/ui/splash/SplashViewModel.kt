package my.id.jeremia.etrash.ui.splash

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import my.id.jeremia.etrash.data.repository.OnBoardRepository
import my.id.jeremia.etrash.data.repository.UserRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val loader: Loader,
    val messenger: Messenger,
    val navigator: Navigator,
    val onBoardRepository: OnBoardRepository,
    val userRepository: UserRepository
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "SplashViewModel"
    }

    init{
        process();
    }

    fun process(){
        Log.d(TAG, "Current Access Token: ${userRepository.getCurrentAccessToken()}")

        if(userRepository.getCurrentAccessToken().isNotEmpty()){
            navigator.navigateTo(Destination.Home.MyHome.route, true);
            return;
        }

        onBoardRepository.isCompleted()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                if (!it) {
                    navigator.navigateTo(Destination.Onboard.route, true)
                }else{
                    navigator.navigateTo(Destination.LoginOrRegister.route, true)
                }
            }
        return;
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