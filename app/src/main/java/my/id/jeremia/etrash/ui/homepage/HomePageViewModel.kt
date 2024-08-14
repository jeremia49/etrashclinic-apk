package my.id.jeremia.etrash.ui.homepage

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import my.id.jeremia.etrash.data.repository.AuthRepository
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
    @ApplicationContext val ctx : Context,
    val authRepository: AuthRepository,
    val userRepository: UserRepository,
) : BaseViewModel(loader,messenger,navigator) {

    companion object {
        const val TAG = "HomepageViewModel"
    }

}

