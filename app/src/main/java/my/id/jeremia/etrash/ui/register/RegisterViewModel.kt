package my.id.jeremia.etrash.ui.register

import Message
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import my.id.jeremia.etrash.data.remote.apis.auth.response.AuthRegisterErrorResponse
import my.id.jeremia.etrash.data.remote.response.ApiErrorResponse
import my.id.jeremia.etrash.data.repository.AuthRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.navigation.Navigator
import my.id.jeremia.etrash.utils.common.isValidEmail
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    val authRepository:AuthRepository,
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "RegisterViewModel"
    }

    private val _name = MutableStateFlow("")
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _passwordConfirm = MutableStateFlow("")
    private val _nohp = MutableStateFlow("")
    private val _tandc = MutableStateFlow(false)
    private val _enableRegisterButton = MutableStateFlow(true)

    private val _nameError = MutableStateFlow("")
    private val _emailError = MutableStateFlow("")
    private val _passwordError = MutableStateFlow("")
    private val _nohpError = MutableStateFlow("")
    private val _passwordConfirmError = MutableStateFlow("")
    private val _tandcError = MutableStateFlow("")

    val name = _name.asStateFlow()
    val email = _email.asStateFlow()
    val password = _password.asStateFlow()
    val passwordConfirm = _passwordConfirm.asStateFlow()
    val enableRegisterButton = _enableRegisterButton.asStateFlow()
    val nohp = _nohp.asStateFlow()
    val tandc = _tandc.asStateFlow()

    val nameError = _nameError.asStateFlow()
    val emailError = _emailError.asStateFlow()
    val passwordError = _passwordError.asStateFlow()
    val passwordConfirmError = _passwordConfirmError.asStateFlow()
    val nohpError = _nohpError.asStateFlow()
    val tandcError = _tandcError.asStateFlow()

    fun onNameChange(input: String) {
        _name.tryEmit(input)
        if (nameError.value.isNotEmpty()) _nameError.tryEmit("")
    }

    fun onEmailChange(input: String) {
        _email.tryEmit(input)
        if (emailError.value.isNotEmpty()) _emailError.tryEmit("")
    }

    fun onPasswordChange(input: String) {
        _password.tryEmit(input)
        if (passwordError.value.isNotEmpty()) _passwordError.tryEmit("")
    }

    fun onPasswordConfirmationChange(input: String) {
        _passwordConfirm.tryEmit(input)
        if (passwordConfirmError.value.isNotEmpty()) _passwordConfirmError.tryEmit("")
    }

    fun onNohpChange(input: String) {
        _nohp.tryEmit(input)
        if (nohpError.value.isNotEmpty()) _nohpError.tryEmit("")
    }

    fun onTandcChange(input: Boolean) {
        _tandc.tryEmit(input)
        if (_tandc.value){
            _tandcError.tryEmit("")
        }
    }

    fun navLogin() {
        navigator.navigateTo(Destination.Login.route)
    }

    private fun validate(): Boolean {
        var error = false
        if (!email.value.isValidEmail()) _emailError.tryEmit("Invalid Email").run { error = true }
        if (password.value.length < 8) _passwordError.tryEmit("Password length should be at least 8")
            .run { error = true }
        if (name.value.isEmpty()) _nameError.tryEmit("Nama tidak boleh kosong")
            .run { error = true }
        if (password.value != passwordConfirm.value) _passwordConfirmError.tryEmit("Password dan Password Konfirmasi berbeda")
            .run { error = true }
        if (nohp.value.length < 11) _nohpError.tryEmit("No HP minimal 11 digit")
            .run { error = true }
        if (!tandc.value) _tandcError.tryEmit("Terms and Conditions harus diterima")
            .run { error = true }
        return !error
    }


    fun doRegister() {
        if (validate()) {
            _enableRegisterButton.tryEmit(false)
            launchNetwork(
                error = {
                    if(it.status != ApiErrorResponse.Status.API_ERROR ){
                        return@launchNetwork;
                    }

                    try{
                        val error = Moshi.Builder()
                            .build()
                            .adapter(AuthRegisterErrorResponse::class.java)
                            .fromJson(it.fullResponse)

                        if(error!!.status == "error"){
                            if(error.reason?.name != null){
                                _nameError.tryEmit(error.reason.name[0]!!)
                            }
                            if(error.reason?.email != null){
                                _emailError.tryEmit(error.reason.email[0]!!)
                            }
                            if(error.reason?.password != null ){
                                _passwordError.tryEmit(error.reason.password[0]!!)
                            }
                        }

                    }catch(_: Exception){}
                },
                finish = {
                    _enableRegisterButton.tryEmit(true)
                },
            ) {
                authRepository.doRegister(email.value, name.value, nohp.value, password.value, )
                    .collect {
                        messenger.deliver(Message.success("Berhasil membuat akun, silahkan login kembali"))
                        navigator.navigateTo(Destination.Login.route, true)
                    }
            }

        }

    }

}