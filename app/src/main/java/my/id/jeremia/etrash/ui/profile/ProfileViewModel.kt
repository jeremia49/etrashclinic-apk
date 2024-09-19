package my.id.jeremia.etrash.ui.profile

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import my.id.jeremia.etrash.data.model.Article
import my.id.jeremia.etrash.data.model.Informasi
import my.id.jeremia.etrash.data.model.Me
import my.id.jeremia.etrash.data.model.ProdukHasil
import my.id.jeremia.etrash.data.remote.apis.data.updateprofile.request.UpdateProfileRequest
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.data.repository.UserRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.homepage.HomePageViewModel
import my.id.jeremia.etrash.ui.homepage.HomePageViewModel.Companion
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx: Context,
    val dataRepository: DataRepository,
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "ProfileViewModel"
    }

    private val _namapengguna = MutableStateFlow("")
    private val _photoUrl = MutableStateFlow("")
    private val _nohp = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _passwordConfirm = MutableStateFlow("")

    var selectedImage by  mutableStateOf<Uri?>(null)


    val namapengguna = _namapengguna.asStateFlow()
    val photoUrl = _photoUrl.asStateFlow()
    val nohp = _nohp.asStateFlow()
    val password = _password.asStateFlow()
    val passwordConfirm = _passwordConfirm.asStateFlow()

    fun onNameChange(input: String) {
        _namapengguna.tryEmit(input)
    }
    fun onNohpChange(input: String) {
        _nohp.tryEmit(input)
    }
    fun onPasswordChange(input: String) {
        _password.tryEmit(input)
    }
    fun onPasswordConfirmChange(input: String) {
        _passwordConfirm.tryEmit(input)
    }


    fun updateMe() {
        launchNetwork {
            val meResponse = dataRepository.getMe().first().data
            _namapengguna.value = meResponse!!.name!!
            _photoUrl.value = meResponse!!.photoUrl!!
            _nohp.value =  meResponse!!.nohp!!
        }
    }


    init {
        updateMe()
    }

    fun saveProfile(jenis:String){
        if(jenis.equals("profile")){
            launchNetwork {
                val data = dataRepository.updateProfile(
                    UpdateProfileRequest(
                        name = namapengguna.value,
                        nohp = nohp.value,
                        photoUrl = photoUrl.value,
                    )
                ).first()
                if(data.status!!.equals("ok")){
                    messenger.deliver(Message.success("Berhasil update profil"))
                    navigator.navigateBack()
                }
            }
        }else if(jenis.equals("password")){
            if(password.value.length >=8 ){
                if(password.value.equals(passwordConfirm.value)){
                    launchNetwork {
                        val data = dataRepository.updateProfile(
                            UpdateProfileRequest(
                                password = password.value,
                            )
                        ).first()
                        if(data.status!!.equals("ok")){
                            messenger.deliver(Message.success("Berhasil update password"))
                            navigator.navigateBack()
                        }
                    }
                }else{
                    messenger.deliver(Message.error("Password Confirm Salah !"))
                }
            }else{
                messenger.deliver(Message.error("Password minimal 8 karakter !"))
            }


        }

    }

    fun uploadImage(path:String){
        launchNetwork {
            val data = dataRepository.uploadImage(path).first()
            _photoUrl.value = data.data!!
        }
    }

}
