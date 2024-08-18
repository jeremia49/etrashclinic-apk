package my.id.jeremia.etrash.ui.uploadsampah

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import my.id.jeremia.etrash.data.model.Sampah
import my.id.jeremia.etrash.data.model.SampahUnitPrice
import my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response.SampahUnitPriceSuccessResponse
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class UploadSampahViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx: Context,
    val dataRepository: DataRepository,
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "UploadSampahViewModel"
    }

    private val _sampahunitprice = MutableStateFlow(emptyList<SampahUnitPrice>())
    val sampahUnitPrices = _sampahunitprice.asStateFlow()

    var sampahList by mutableStateOf(listOf<Sampah>())
    var beratList by mutableStateOf(listOf<Int>())
    var photoList by  mutableStateOf(listOf<String>())

    var activeIdx by  mutableStateOf(-1)
    var selectedImage by  mutableStateOf<Uri?>(null)


    init{
        updateSampahUnitPrices()
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
                    minprice = it.minprice,
                    maxprice = it.maxprice,
                    satuan = it.satuan,
                )
            }
            _sampahunitprice.emit(sampahunitprice)
        }
    }

    fun uploadImage(idx: Int, path:String){
        launchNetwork {
            val data = dataRepository.uploadImage(path).first()
            photoList = photoList.mapIndexed { index, i ->
                if (index == idx) data.data!! else i
            }
        }
    }

}