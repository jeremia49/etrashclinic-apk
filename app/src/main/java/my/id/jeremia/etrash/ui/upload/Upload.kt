package my.id.jeremia.etrash.ui.upload

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.data.model.Sampah
import my.id.jeremia.etrash.data.model.SampahUnitPrice
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.image.NetworkImage
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.utils.common.askPermission
import kotlin.math.floor


@Composable
fun UploadView(modifier: Modifier = Modifier, viewModel: UploadViewModel) {

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { _ ->
        viewModel.updatePermissionState();
    }

    BackgroundImage {
        UploadPage(
            modifier = modifier,
            sampahUnitPrice = viewModel.sampahUnitPrices.collectAsStateWithLifecycle().value,
            cameraPermission = viewModel.cameraPermission.value,
            requestCamera = {
                askPermission(
                    arrayOf(Manifest.permission.CAMERA),
                    launcherMultiplePermissions
                )
            },
            openCamera = {
                viewModel.navigator.navigateTo(Destination.Home.Camera.route)
//                viewModel.navigator.navigateTo(Destination.Home.UploadSampah.route)

            }

        )
    }
}


@Composable
fun UploadPage(
    modifier: Modifier = Modifier,
    sampahUnitPrice: List<SampahUnitPrice> = emptyList(),
    cameraPermission: Boolean = false,
    openCamera: () -> Unit = {},
    requestCamera: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Harga Sampah", fontSize = 24.sp, fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier.height(15.dp),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 columns
            modifier = Modifier,

            ) {
            repeat(sampahUnitPrice.size) { idx ->
                item {
                    SampahUnitItem(
                        sampahUnitPrice.get(idx).title!!,
                        sampahUnitPrice.get(idx).imgPublicUrl!!,
                        "${sampahUnitPrice.get(idx).rupiahPrice}",
                        sampah = Sampah(
                            id = sampahUnitPrice.get(idx).id ?: 0,
                            title = sampahUnitPrice.get(idx).title!!,
                            berat = -1,
                            pictureUrl = sampahUnitPrice.get(idx).imgPublicUrl!!,
                            rupiahPrice = sampahUnitPrice.get(idx).rupiahPrice!!,
                            satuan = sampahUnitPrice.get(idx).satuan!!,
                        ),
                        satuan = sampahUnitPrice.get(idx).satuan!!,
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        if (cameraPermission) {
            Button(
                onClick = {
                    openCamera()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // replace with your button color
                modifier = Modifier
            ) {
                Text(text = "Buka Camera", color = Color.White)
            }

        } else {
            Button(
                onClick = {
                    requestCamera()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // replace with your button color
                modifier = Modifier
            ) {
                Text(text = "Perbolehkan Akses Camera", color = Color.White)
            }

        }

    }
}


@Composable
fun SampahUnitItem(
    title: String,
    thumbnailUrl: String,
    harga: String,
    satuan: String,
    onItemClick: (Sampah) -> Unit = {},
    sampah: Sampah
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(sampah)
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NetworkImage(
            url = thumbnailUrl,
            contentDescription = "Gambar Sampah",
            modifier = Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.coin), contentDescription = "Coin."
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${Integer.parseInt(harga)/1000.0}", fontSize = 14.sp, color = Color.Gray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Key, contentDescription = "Satuan"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = satuan, fontSize = 14.sp, color = Color.Gray
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun UploadScreenPreview() {
    UploadPage()
}