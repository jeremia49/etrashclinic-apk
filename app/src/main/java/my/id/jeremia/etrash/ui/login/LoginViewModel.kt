package my.id.jeremia.etrash.ui.login

import Message
import android.content.Context
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import my.id.jeremia.etrash.data.model.Auth
import my.id.jeremia.etrash.data.remote.apis.auth.response.AuthLoginErrorResponse
import my.id.jeremia.etrash.data.remote.response.ApiErrorResponse
import my.id.jeremia.etrash.data.repository.AuthRepository
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.data.repository.UserRepository
import my.id.jeremia.etrash.init.FirebaseInit
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.navigation.Navigator
import my.id.jeremia.etrash.utils.common.isValidEmail
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx: Context,
    val authRepository: AuthRepository,
    val userRepository: UserRepository,
    val dataRepository: DataRepository,
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "LoginViewModel"
    }

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _emailError = MutableStateFlow("")
    private val _passwordError = MutableStateFlow("")
    private val _enableLoginButton = MutableStateFlow(true)


    val email = _email.asStateFlow()
    val password = _password.asStateFlow()
    val emailError = _emailError.asStateFlow()
    val passwordError = _passwordError.asStateFlow()
    val enableLoginButton = _enableLoginButton.asStateFlow()

    fun onEmailChange(input: String) {
        _email.tryEmit(input)
        if (emailError.value.isNotEmpty()) _emailError.tryEmit("")
    }

    fun onPasswordChange(input: String) {
        _password.tryEmit(input)
        if (passwordError.value.isNotEmpty()) _passwordError.tryEmit("")
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun doLogin() {
        if (validate()) {
            _enableLoginButton.tryEmit(false)
            launchNetwork(
                error = {
                    if (it.status != ApiErrorResponse.Status.API_ERROR) {
                        return@launchNetwork;
                    }

                    try {
                        val error = Moshi.Builder()
                            .build()
                            .adapter(AuthLoginErrorResponse::class.java)
                            .fromJson(it.fullResponse)

                        if (error!!.status == "error") {
                            if (error.message == "Wrong email / password") {
                                _emailError.tryEmit("")
                                _passwordError.tryEmit("Email atau password yang anda masukkan salah")
                            }

                            if (error.reason?.email != null) {
                                _emailError.tryEmit(error.reason.email[0]!!)
                            }
                            if (error.reason?.password != null) {
                                _passwordError.tryEmit(error.reason.password[0]!!)
                            }
                        }

                    } catch (_: Exception) {
                    }
                },
                finish = {
//                    _enableLoginButton.tryEmit(true)
                },
            ) {
                _emailError.tryEmit("")
                _passwordError.tryEmit("")

                authRepository
                    .doLogin(email.value, password.value)
                    .flatMapLatest {
                        flow {

                            userRepository.saveCurrentAuth(
                                Auth(
                                    it.data!!.uid!!.toString(),
                                    it.data.name!!,
                                    it.data.email!!,
                                    it.data.nohp!!,
                                    it.data.accessToken!!,
                                    it.data.photoUrl!!
                                )

                            ).blockingSubscribe()

                            emit(it)

                        }
                    }
                    .collect {
                        val firebaseToken = userRepository.getFirebaseToken().blockingFirst()
                        if (firebaseToken.isNotEmpty()) {
                            dataRepository.sendFCMToken(firebaseToken)
                                .catch {
                                    userRepository.setFirebaseTokenSent(true)
                                }
                                .collect {
                                    userRepository.setFirebaseTokenSent(true)

                                    messenger.deliver(Message.success("Berhasil login"))
                                    navigator.navigateTo(Destination.Home.MyHome.route, true)
                                }
                        } else {
                            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                                if (!task.isSuccessful) {
                                    Log.e(
                                        FirebaseInit.TAG,
                                        "Fetching FCM registration token failed :" + task.exception.toString()
                                    )
                                    return@addOnCompleteListener
                                }
                                val token = task.result

                                userRepository.setFirebaseToken(token).subscribe()
                                GlobalScope.launch {
                                    dataRepository.sendFCMToken(token)
                                        .catch { }
                                        .collect {
                                            userRepository.setFirebaseTokenSent(true).subscribe()

                                            messenger.deliver(Message.success("Berhasil login"))
                                            navigator.navigateTo(
                                                Destination.Home.MyHome.route,
                                                true
                                            )
                                        }
                                }

                            }
                        }

                    }

            }

        }

    }


    fun navRegister() {
        navigator.navigateTo(Destination.Register.route)
    }

    private fun validate(): Boolean {
        var error = false
        if (!email.value.isValidEmail()) _emailError.tryEmit("Invalid Email").run { error = true }
        if (password.value.length < 8) _passwordError.tryEmit("Password length should be at least 8")
            .run { error = true }
        return !error
    }

}