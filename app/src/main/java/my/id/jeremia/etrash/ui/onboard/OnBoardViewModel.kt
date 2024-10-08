package my.id.jeremia.etrash.ui.onboard

import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.jeremia.etrash.data.repository.OnBoardRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    private val onBoardRepository: OnBoardRepository,
) : BaseViewModel(loader, messenger, navigator) {

    fun setCompleted() {
        onBoardRepository.setOnBoard(true)
        navigator.navigateTo(Destination.LoginOrRegister.route,true);
    }


}