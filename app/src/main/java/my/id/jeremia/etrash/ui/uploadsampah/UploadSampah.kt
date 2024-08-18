package my.id.jeremia.etrash.ui.uploadsampah

import android.content.Context
import android.net.Network
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.util.query
import coil.compose.AsyncImage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import my.id.jeremia.etrash.data.model.Sampah
import my.id.jeremia.etrash.data.model.SampahUnitPrice
import my.id.jeremia.etrash.data.remote.apis.data.sampahunitprice.response.SampahUnitPriceSuccessResponse
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.image.NetworkImage
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.upload.SampahUnitItem
import my.id.jeremia.etrash.utils.common.saveImageToStorage
import java.io.BufferedReader
import java.io.InputStreamReader


@Composable
fun UploadSampahView(modifier: Modifier = Modifier, viewModel: UploadSampahViewModel) {
    var isAddingSampah by remember { mutableStateOf(false) }
    var page by remember { mutableStateOf(1) }

    val context = LocalContext.current

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if (it != null) {
                viewModel.selectedImage = it
                viewModel.uploadImage(
                    viewModel.activeIdx,
                    saveImageToStorage(context, viewModel.selectedImage!!)!!
                )
            }
        }
    )


    BackHandler {
        if (page > 1) {
            page -= 1
        } else {
            viewModel.navigator.navigateTo(Destination.Home.Upload.route)
        }
    }

    BackgroundImage {
        if (isAddingSampah) {
            AddSampahPage(
                modifier = modifier,
                sampahUnitPrice = viewModel.sampahUnitPrices.collectAsStateWithLifecycle().value,
                onAddingSampah = {
                    isAddingSampah = false
                    viewModel.sampahList = viewModel.sampahList.plus(it)
                    viewModel.beratList = viewModel.beratList.plus(0)
                    viewModel.photoList = viewModel.photoList.plus("")
                },
            )
        } else {
            UploadSampahPage(
                modifier,
                page,
                sampahList = viewModel.sampahList,
                beratList = viewModel.beratList,
                photoList = viewModel.photoList,
                onAddingSampah = { isAddingSampah = true },
                onNextPage = { page += 1 },
                onPrevPage = { page -= 1 },
                onDeleteClick = {
                    viewModel.sampahList =
                        viewModel.sampahList.filterIndexed { index, _ -> index != it }
                    viewModel.beratList =
                        viewModel.beratList.filterIndexed { index, _ -> index != it }
                    viewModel.photoList =
                        viewModel.photoList.filterIndexed { index, _ -> index != it }
                },
                onAddingBerat = {
                    viewModel.beratList = viewModel.beratList.mapIndexed { index, i ->
                        if (index == it) i + 1 else i
                    }
                },
                onRemovingBerat = {
                    viewModel.beratList = viewModel.beratList.mapIndexed { index, i ->
                        if (index == it) i - 1 else i
                    }
                },
                onAddingPhoto = { idx ->
                    viewModel.activeIdx = idx
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },

                )
        }
    }
}

@Composable
fun AddSampahPage(
    modifier: Modifier = Modifier,
    sampahUnitPrice: List<SampahUnitPrice>,
    onAddingSampah: (Sampah) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            repeat(sampahUnitPrice.size) { idx ->
                item {
                    SampahUnitItem(
                        sampahUnitPrice.get(idx).title!!,
                        sampahUnitPrice.get(idx).imgPublicUrl!!,
                        "${sampahUnitPrice.get(idx).minprice}-${sampahUnitPrice.get(idx).maxprice}",
                        sampah = Sampah(
                            id = sampahUnitPrice.get(idx).id ?: 0,
                            title = sampahUnitPrice.get(idx).title!!,
                            berat = -1,
                            pictureUrl = sampahUnitPrice.get(idx).imgPublicUrl!!,
                            minPrice = sampahUnitPrice.get(idx).minprice!!,
                            maxPrice = sampahUnitPrice.get(idx).maxprice!!,
                            satuan = sampahUnitPrice.get(idx).satuan!!,
                        ),
                        onItemClick = onAddingSampah
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

}


@Composable
fun UploadSampahPage(
    modifier: Modifier = Modifier,
    page: Int = 1,
    onNextPage: () -> Unit = {},
    onPrevPage: () -> Unit = {},
    onAddingSampah: () -> Unit = {},
    sampahList: List<Sampah> = emptyList(),
    onDeleteClick: (Int) -> Unit = {},
    onAddingBerat: (Int) -> Unit = {},
    onRemovingBerat: (Int) -> Unit = {},
    onAddingPhoto: (Int) -> Unit = {},
    beratList: List<Int> = emptyList<Int>(),
    photoList: List<String> = emptyList<String>(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        // Progress Steps
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            item {
                StepItem(isActive = page >= 1, text = "Pilih Jenis\nSampah")
            }
            item {
                StepItem(isActive = page >= 2, text = "Atur Berat\nSampah")
            }
            item {
                StepItem(isActive = page >= 3, text = "Upload Foto\nSampah")
            }
            item {
                StepItem(isActive = page >= 4, text = "Konfirmasi")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Items List
        Column(
            modifier = Modifier
        ) {
            repeat(sampahList.size) { idx ->
                UploadItem(
                    title = sampahList.get(idx).title,
                    estimatedIncome = "Rp. X - Rp. X",
                    estimatedCoins = "${sampahList.get(idx).minPrice} - ${sampahList.get(idx).maxPrice}",
                    imgUrl = sampahList.get(idx).pictureUrl,
                    onDeleteClick = onDeleteClick,
                    page = page,
                    idx = idx,
                    berat = beratList.get(idx),
                    photo = photoList.get(idx),
                    onAddingBerat = onAddingBerat,
                    onRemovingBerat = onRemovingBerat,
                    onAddingPhoto = onAddingPhoto
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (page == 1) {
            // Add More Items Button
            Button(
                onClick = onAddingSampah,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "+ Tambah sampah lainnya", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNextPage,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Selanjutnya", color = Color.White)
        }
    }
}


@Composable
fun StepItem(isActive: Boolean, text: String) {
    Column(
        modifier = Modifier
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = if (isActive) Color.Green else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text, color = if (isActive) Color.Black else Color.Gray, fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun UploadItem(
    title: String,
    estimatedIncome: String,
    estimatedCoins: String,
    imgUrl: String,
    onDeleteClick: (Int) -> Unit = {},
    idx: Int = 0,
    page: Int = 1,
    berat: Int = -1,
    photo: String = "",
    onAddingBerat: (Int) -> Unit = {},
    onRemovingBerat: (Int) -> Unit = {},
    onAddingPhoto: (Int) -> Unit = { },
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                url = imgUrl,
                contentDescription = "Gambar Sampah",
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Estimasi saldo dan coin:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = "$estimatedIncome",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = "$estimatedCoins",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            if (page == 1) {
                IconButton(onClick = { onDeleteClick(idx) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Hapus", tint = Color.Red)
                }
            } else if (page == 2) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        IconButton(onClick = {
                            if (berat > 0)
                                onRemovingBerat(idx)
                        }) {
                            Icon(Icons.Default.Remove, contentDescription = "Kurang 1")
                        }

                        Text(text = berat.toString())

                        IconButton(onClick = {
                            onAddingBerat(idx)
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Tambah 1")
                        }
                    }
                }
            } else if (page == 3) {
                Column {
                    if (photo.contentEquals("")) {
                        IconButton(onClick = {
                            onAddingPhoto(idx)
                        }) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Tambah",
                                tint = Color.Green
                            )
                        }
                    } else {
                        NetworkImage(
                            url = photo,
                            contentDescription = "Gambar Sampah",
                            modifier = Modifier
                                .size(64.dp),
                        )
                    }
                }
            } else if (page == 4) {
                Column {
                    if (photo.contentEquals("")) {
                        IconButton(onClick = {
                            onAddingPhoto(idx)
                        }) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Tambah",
                                tint = Color.Green
                            )
                        }
                    } else {
                        NetworkImage(
                            url = photo,
                            contentDescription = "Gambar Sampah",
                            modifier = Modifier
                                .size(64.dp),
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun UploadSampahPagePreview() {
    UploadSampahPage()
}