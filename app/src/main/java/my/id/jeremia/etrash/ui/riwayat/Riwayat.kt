package my.id.jeremia.etrash.ui.riwayat

import Message
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Sync
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
import my.id.jeremia.etrash.data.remote.apis.data.history.response.HistorySucessResponse
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.image.NetworkImage
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.utils.common.CalendarUtils.getDateFromString
import my.id.jeremia.etrash.utils.common.CalendarUtils.getFormattedDateTime
import kotlin.math.floor
import kotlin.math.roundToInt

@Composable
fun RiwayatView(modifier: Modifier = Modifier, viewModel: RiwayatViewModel) {
    BackgroundImage {
        RiwayatPage(
            modifier = modifier,
            data = viewModel.historySampah.collectAsStateWithLifecycle().value,
            messenger = viewModel.messenger
        )
    }
}

@Composable
fun RiwayatPage(
    modifier: Modifier = Modifier,
    data: List<HistorySucessResponse.Data> = emptyList(),
    messenger: Messenger = Messenger()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//        HeaderSection(username, photoUrl)
        Text(
            "History", fontSize = 24.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            repeat(data.size) { idx ->
                item {
                    RiwayatItem(
                        title = data[idx].title!!,
                        thumbnailUrl = data[idx].imgUrl!!,
                        tanggal = data[idx].createdAt!!,
                        isApproved = data[idx].isApproved!! == 1,
                        isDeclined = data[idx].isDeclined!! == 1,
                        onClick = {
                            if (data[idx].isApproved!! == 1) {
                                messenger.deliver(Message.success("Transaksi Disetujui"))
                            } else if (data[idx].isDeclined!! == 1) {
                                messenger.deliver(Message.error("Transaksi Ditolak"))
                            } else {
                                messenger.deliver(Message.info("Transaksi Sedang Diproses"))
                            }
                        },
                        coin = floor(data[idx].origprice!!.toDouble() / 1000.0 * data[idx].total!!).roundToInt()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun RiwayatItem(
    title: String,
    thumbnailUrl: String,
    tanggal: String,
    isApproved: Boolean = false,
    isDeclined: Boolean = false,
    coin: Int = 0,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically

    ) {
        NetworkImage(
            url = thumbnailUrl, contentDescription = "Gambar Artikel",
            modifier = Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.CalendarMonth,
                    contentDescription = "Tanggal",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = getFormattedDateTime(getDateFromString(tanggal)!!)!!,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = R.drawable.coin), contentDescription = "Coin",
                    modifier = Modifier
                        .size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${coin}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        if (isApproved) {
            Icon(
                painterResource(id = R.drawable.success),
                contentDescription = "Approved",
                tint = Color.Green
            )
        } else if (isDeclined) {
            Icon(
                painterResource(id = R.drawable.warning),
                contentDescription = "Rejected",
                tint = Color.Red
            )
        } else {
            Icon(Icons.Default.Sync, contentDescription = "Pending", tint = Color.Black)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RiwayatPreview() {
    RiwayatPage()
}