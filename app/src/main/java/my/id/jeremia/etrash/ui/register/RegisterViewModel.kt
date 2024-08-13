package my.id.jeremia.etrash.ui.register

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "RegisterViewModel"
    }

    private val _name = MutableStateFlow("")
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _passwordConfirm = MutableStateFlow("")
    private val _enableRegisterButton = MutableStateFlow(true)

    private val _nameError = MutableStateFlow("")
    private val _emailError = MutableStateFlow("")
    private val _passwordError = MutableStateFlow("")
    private val _passwordConfirmError = MutableStateFlow("")

    val name = _name.asStateFlow()
    val email = _email.asStateFlow()
    val password = _password.asStateFlow()
    val passwordConfirm = _passwordConfirm.asStateFlow()
    val enableRegisterButton = _enableRegisterButton.asStateFlow()

    val nameError = _nameError.asStateFlow()
    val emailError = _emailError.asStateFlow()
    val passwordError = _passwordError.asStateFlow()
    val passwordConfirmError = _passwordConfirmError.asStateFlow()

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

    fun navLogin() {
        navigator.navigateTo(Destination.Login.route)
    }

    private fun validate(): Boolean {
        var error = false
//        if (!email.value.isValidEmail()) _emailError.tryEmit("Invalid Email").run { error = true }
        if (password.value.length < 8) _passwordError.tryEmit("Password length should be at least 8")
            .run { error = true }
        if (name.value.isEmpty()) _nameError.tryEmit("Nama tidak boleh kosong")
            .run { error = true }
        if (password.value != passwordConfirm.value) _passwordConfirmError.tryEmit("Password dan Password Konfirmasi berbeda")
            .run { error = true }
        return !error
    }

}