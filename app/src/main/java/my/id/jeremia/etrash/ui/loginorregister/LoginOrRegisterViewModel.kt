package my.id.jeremia.etrash.ui.loginorregister

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class LoginOrRegisterViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx: Context,
) : BaseViewModel(loader, messenger, navigator) {

    fun login(){
        navigator.navigateTo(Destination.Login.route);
    }

    fun register(){
        navigator.navigateTo(Destination.Register.route);
    }

}