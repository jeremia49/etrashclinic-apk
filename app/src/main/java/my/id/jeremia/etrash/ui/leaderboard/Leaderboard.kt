package my.id.jeremia.etrash.ui.leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.data.remote.apis.data.leaderboard.response.CurrentLeaderboardSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.leaderboard.response.OldLeaderboardSuccessResponse
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.image.NetworkImage
import my.id.jeremia.etrash.ui.leagues.LeagueItem
import my.id.jeremia.etrash.ui.navigation.Destination

@Composable
fun LeaderboardView(modifier: Modifier = Modifier, viewModel: LeaderboardViewModel) {
    BackgroundImage {
        LeaderboardUI(
            modifier,
            currentLeague = viewModel.currentLeague.collectAsStateWithLifecycle().value,
            currentLeagueText = viewModel.currentLeagueText.collectAsStateWithLifecycle().value,
            currentLeagueList = viewModel.currentLeagueList.collectAsStateWithLifecycle().value,
            pastLeagueMap = viewModel.pastLeagueMap.collectAsStateWithLifecycle().value,
            onClickMore = {
                viewModel.navigator.navigateTo(Destination.Home.Leagues.route)
            }
        )
    }
}

@Composable
fun LeaderboardUI(
    modifier: Modifier = Modifier,
    currentLeague: String = "",
    currentLeagueText:String = "",
    currentLeagueList : List<CurrentLeaderboardSuccessResponse.Data> = emptyList(),
    pastLeagueMap : LinkedHashMap<String, List<OldLeaderboardSuccessResponse.Data>> = linkedMapOf(),
    onClickMore: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text("Leaderboards", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LeaderboardHeader(
            currentLeague = currentLeague,
            onClickMore = onClickMore
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (!currentLeague.contentEquals("")) {
            LeaderboardSection(
                currentLeagueText, currentLeagueList.map{
                    LeaderboardItem(it.name!!, it.totalSampahBulanIni!!, it.photoUrl!!)
                }
            )
        }

        pastLeagueMap.forEach {
            Spacer(modifier = Modifier.height(16.dp))
            LeaderboardSection(
                it.key, it.value.map{
                    LeaderboardItem(it.name!!, it.totalSampah!!, it.photoUrl!!)
                }
            )
        }

    }
}

@Composable
fun LeaderboardHeader(
    modifier: Modifier = Modifier,
    currentLeague: String = "",
    onClickMore: () -> Unit = {}
) {
    if (currentLeague.contentEquals("")) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Anda belum masuk ke League bulan ini, silahkan mulai transaksi dengan kami...")
                Button(onClick = onClickMore) {
                    Text(text = "Lihat Selengkapnya")
                }
            }
        }
    } else {
        val icon = remember{
            when(currentLeague){
                "ascendant" -> R.drawable.ascendant
                "diamond" -> R.drawable.diamond
                "platinum" -> R.drawable.platinum
                "gold" -> R.drawable.gold
                else -> R.drawable.silver
            }
        }
        val rank = remember{
            when(currentLeague){
                "ascendant" -> "ASCENDANT"
                "diamond" -> "DIAMOND"
                "platinum" ->"PLATINUM"
                "gold" -> "GOLD"
                else -> "SILVER"
            }
        }
        val color = remember{
            when(currentLeague){
                "ascendant" ->  Color(0xFFFFAFAF)
                "diamond" ->Color(0xFFE1BEE7)
                "platinum" ->Color(0xFFB2EBF2)
                "gold" -> Color(0xFFFFF59D)
                else -> Color(0xFFBDBDBD)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color, shape = MaterialTheme.shapes.medium)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(id = icon),
                contentDescription = currentLeague,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = rank,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowRight,
                contentDescription = "Next",
                tint = Color.Gray,
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        onClickMore()
                    }
            )
        }

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
        NetworkImage(
           item.profileImage,
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

data class LeaderboardItem(val name: String, val weight: Int, val profileImage: String)

@Preview(showBackground = true)
@Composable
fun PreviewLeaderboardUI() {
    LeaderboardUI()
}