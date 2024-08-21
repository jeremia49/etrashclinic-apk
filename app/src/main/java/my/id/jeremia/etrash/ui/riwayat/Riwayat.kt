package my.id.jeremia.etrash.ui.riwayat

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.id.jeremia.etrash.data.remote.apis.data.history.response.HistorySucessResponse
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.image.NetworkImage
import my.id.jeremia.potholetracker.utils.common.CalendarUtils.getDateFromString
import my.id.jeremia.potholetracker.utils.common.CalendarUtils.getFormattedDateTime

@Composable
fun RiwayatView(modifier: Modifier = Modifier, viewModel: RiwayatViewModel) {
    BackgroundImage {
        RiwayatPage(
            modifier = modifier,
            data = viewModel.historySampah.collectAsStateWithLifecycle().value
            )
    }
}

@Composable
fun RiwayatPage(modifier: Modifier = Modifier, data:List<HistorySucessResponse.Data> = emptyList()) {
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
                item{
                    RiwayatItem(
                        title = data[idx].title!!,
                        thumbnailUrl = data[idx].imgUrl!!,
                        tanggal = data[idx].createdAt!!,
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun RiwayatItem(title: String, thumbnailUrl: String, tanggal: String, onClick: () -> Unit = {}) {
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
                .weight(1f)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Row {
                Icon(Icons.Default.CalendarMonth, contentDescription = "Tanggal")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = getFormattedDateTime(getDateFromString(tanggal)!!)!!,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RiwayatPreview() {
    RiwayatPage()
}