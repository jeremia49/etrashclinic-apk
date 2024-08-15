package my.id.jeremia.etrash.ui.common.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.ui.common.image.NetworkImage
import my.id.jeremia.etrash.ui.common.text.AutoResizeText
import my.id.jeremia.etrash.ui.common.text.FontSizeRange


@Composable
fun HeaderSection(
    username: String,
    photoUrl: String,
    isRoot: Boolean = true,
    isNewNotificationExist: Boolean = false,
    onClickLeaderboard: () -> Unit = {},
    onClickNotification: () -> Unit = {},
    onClickProfilePicture: () -> Unit = {},
    onClickBack: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isRoot) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Halo,", fontSize = 20.sp)
                AutoResizeText(
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = "${username}!",
                    fontSizeRange = FontSizeRange(
                        min = 12.sp,
                        max = 24.sp
                    ),
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Row(modifier = Modifier
                .clickable {
                    onClickBack()
                }
                .weight(1f)
            ) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back")
                Text("Kembali", fontSize = 16.sp)
            }
        }
        Image(
            painter = painterResource(id = R.drawable.leaderboard),
            contentDescription = "Leaderboard",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    onClickLeaderboard()
                }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = if (isNewNotificationExist) R.drawable.notification_exist else R.drawable.notification),
            contentDescription = "Notification",
            modifier = Modifier
                .clickable {
                    onClickNotification()
                }
                .size(30.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        NetworkImage(
            url = photoUrl,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable {
                    onClickProfilePicture()
                }
        )
    }
}