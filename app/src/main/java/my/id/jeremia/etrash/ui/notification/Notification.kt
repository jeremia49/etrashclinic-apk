package my.id.jeremia.etrash.ui.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.id.jeremia.etrash.data.remote.apis.data.notifications.response.NotificationSuccessResponse
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.utils.common.CalendarUtils.getDateFromString
import my.id.jeremia.etrash.utils.common.CalendarUtils.getFormattedDateTime

@Composable
fun NotificationView(modifier: Modifier = Modifier, viewModel: NotificationViewModel) {
    BackgroundImage {
        NotificationPage(
            modifier = modifier,
            data = viewModel.notifications.collectAsStateWithLifecycle().value,
        )
    }
}


@Composable
fun NotificationPage(
    modifier: Modifier = Modifier,
    data: List<NotificationSuccessResponse.Data> = emptyList(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Notifikasi", fontSize = 24.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            repeat(data.size) { idx ->
                item {
                    NotificationItem(
                        timestamp = data[idx].createdAt!!,
                        type = data[idx].type!!,
                        title = data[idx].title!!,
                        content = data[idx].content!!
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun NotificationItem(
    timestamp: String,
    type: String,
    title: String,
    content: String,
) {
    Row {
        Icon(
            if (type == "SAMPAH") Icons.Default.RestoreFromTrash else
                Icons.Default.CreditCard, contentDescription = "Icon"
        )

        Column {
            Text(getFormattedDateTime(getDateFromString(timestamp)!!)!!)
            Text(title, fontWeight = FontWeight.Bold)
            Text(content)
        }
    }
}

