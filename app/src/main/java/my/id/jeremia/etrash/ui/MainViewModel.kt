package my.id.jeremia.etrash.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val loader: Loader,
    val navigator:Navigator,
    val messenger: Messenger,
) :BaseViewModel(loader,messenger, navigator){

    companion object {
        const val TAG = "MainViewModel"
    }

    init {

    }

}