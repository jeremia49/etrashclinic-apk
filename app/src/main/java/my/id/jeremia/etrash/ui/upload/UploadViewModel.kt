package my.id.jeremia.etrash.ui.upload

import android.Manifest
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import my.id.jeremia.etrash.data.model.SampahUnitPrice
import my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response.SampahUnitPriceSuccessResponse
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Navigator
import my.id.jeremia.etrash.utils.common.checkPermission
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx : Context,
    val dataRepository: DataRepository,
) : BaseViewModel(loader,messenger,navigator) {

    companion object {
        const val TAG = "UploadViewModel"
    }


    private val _sampahunitprice = MutableStateFlow(emptyList<SampahUnitPrice>())
    private val _cameraPermission = mutableStateOf(false)

    val cameraPermission : State<Boolean> = _cameraPermission
    val sampahUnitPrices = _sampahunitprice.asStateFlow()


    init{
        updateSampahUnitPrices()
        updatePermissionState()
    }

    fun updateSampahUnitPrices() {
        launchNetwork {
            val sampahUnitPrices =
                dataRepository.getSampahUnitPrice().first().data
                    ?: emptyList<SampahUnitPriceSuccessResponse.Data>()
            val sampahunitprice = sampahUnitPrices.map {
                SampahUnitPrice(
                    id=it!!.id,
                    imgPublicUrl = it.imgPublicUrl,
                    title = it.title,
                    rupiahPrice = it.rupiah,
                    satuan = it.satuan,
                )
            }
            _sampahunitprice.emit(sampahunitprice)
        }
    }

    fun updatePermissionState(){
        _cameraPermission.value = checkPermission(
            ctx,
            arrayOf(
                Manifest.permission.CAMERA,
            )
        )
    }
}