package my.id.jeremia.etrash.ui.leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.navigation.Destination

@Composable
fun LeaderboardView(modifier: Modifier = Modifier, viewModel: LeaderboardViewModel) {
    BackgroundImage {
        LeaderboardUI(
            modifier,
            onClickMore = {
                viewModel.navigator.navigateTo(Destination.Home.Leagues.route)
            }
        )
    }
}

@Composable
fun LeaderboardUI(modifier: Modifier = Modifier, onClickMore: () -> Unit = {}) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text("Leaderboards", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LeaderboardHeader(onClickMore)
        Spacer(modifier = Modifier.height(16.dp))
        LeaderboardSection(
            "Juli, 2024", listOf(
                LeaderboardItem("Pengguna 1", 20.0, R.drawable.logo),
                LeaderboardItem("Pengguna 2", 18.0, R.drawable.logo),
                LeaderboardItem("Pengguna 3", 17.5, R.drawable.logo)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
//        LeaderboardSection("Juni, 2024", listOf(
//            LeaderboardItem("Jeremia Manurung", 20.0, R.drawable.logo),
//            LeaderboardItem("Fahruaji", 18.0, R.drawable.logo),
//            LeaderboardItem("Waterflai", 17.5, R.drawable.logo)
//        ))
    }
}

@Composable
fun LeaderboardHeader(onClickMore: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = R.drawable.silver),
            contentDescription = "Silver",
            tint = Color.Gray,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = "Silver",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowRight,
            contentDescription = "Dropdown",
            tint = Color.Gray,
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    onClickMore()
                }
        )
    }
}

@Composable
fun LeaderboardSection(title: String, items: List<LeaderboardItem>) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        items.forEachIndexed { index, item ->
            LeaderboardItemRow(index + 1, item)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun LeaderboardItemRow(rank: Int, item: LeaderboardItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$rank.",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = item.profileImage),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray, CircleShape)
                .padding(2.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${item.weight} Kg",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

data class LeaderboardItem(val name: String, val weight: Double, val profileImage: Int)

@Preview(showBackground = true)
@Composable
fun PreviewLeaderboardUI() {
    LeaderboardUI()
}