package my.id.jeremia.etrash.ui.leagues

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage

@Composable
fun LeaguesView(modifier: Modifier = Modifier) {
    BackgroundImage {
        LeaguesUI(modifier)
    }
}

@Composable
fun LeaguesUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Leagues",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Papan peringkat liga mengurutkan pemain atau tim berdasarkan kinerja mereka. Liga ini bikin kompetisi lebih seru, memotivasi kamu untuk terus meningkatkan performa, dan memungkinkan kamu membandingkan kemampuan serta meraih hadiah sesuai peringkat.",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LeagueItem("Ascendant", "201 ~", Color(0xFFFFAFAF), R.drawable.ascendant)
        Spacer(modifier = Modifier.height(8.dp))
        LeagueItem("Diamond", "101-200", Color(0xFFE1BEE7), R.drawable.diamond)
        Spacer(modifier = Modifier.height(8.dp))
        LeagueItem("Platinum", "51-100", Color(0xFFB2EBF2), R.drawable.platinum)
        Spacer(modifier = Modifier.height(8.dp))
        LeagueItem("Gold", "21-50", Color(0xFFFFF59D), R.drawable.gold)
        Spacer(modifier = Modifier.height(8.dp))
        LeagueItem("Silver", "1-20", Color(0xFFBDBDBD), R.drawable.silver)
    }
}

@Composable
fun LeagueItem(name: String, range: String, backgroundColor: Color, iconResource: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconResource),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = range,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLeaguesUI() {
    LeaguesUI()
}