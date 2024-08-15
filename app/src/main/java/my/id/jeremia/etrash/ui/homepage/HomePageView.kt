package my.id.jeremia.etrash.ui.homepage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.data.model.Article
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.header.HeaderSection
import my.id.jeremia.etrash.ui.common.image.NetworkImage
import my.id.jeremia.etrash.ui.common.text.AutoResizeText
import my.id.jeremia.etrash.ui.common.text.FontSizeRange
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.theme.hijau40

@Composable
fun HomePageView(modifier: Modifier = Modifier, viewModel: HomePageViewModel) {
    BackHandler {
        viewModel.navigator.finish()
    }
    BackgroundImage {
        HomePage(
            modifier = modifier,
            username = viewModel.namapengguna.collectAsStateWithLifecycle().value,
            photoUrl = viewModel.photoUrl.collectAsStateWithLifecycle().value,
            onClickProfilePicture = {viewModel.navigator.navigateTo(Destination.Home.Settings.route)},
            artikels = viewModel.artikels.collectAsStateWithLifecycle().value,

        )
    }

}

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    username: String = "Jeremia",
    photoUrl: String = "https://example.com/photo.jpg",
    onClickProfilePicture : () -> Unit = {},
    artikels : List<Article> = emptyList()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderSection(username, photoUrl, onClickProfilePicture = onClickProfilePicture)
        Spacer(modifier = Modifier.height(16.dp))
        CoinSection()
        Spacer(modifier = Modifier.height(16.dp))
        TopProductsSection()
        Spacer(modifier = Modifier.height(16.dp))
        InformationSection()
        Spacer(modifier = Modifier.height(16.dp))
        LatestReportsSection()
        Spacer(modifier = Modifier.height(16.dp))
        ArticleSection(artikels)
    }
}


@Composable
fun CoinSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color(0xFF409C5F),
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.money),
                contentDescription = "Uang",
                modifier = Modifier
                    .padding(start = 150.dp)
                    .align(Alignment.CenterEnd),
            )
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "5.000 Coins", fontSize = 16.sp, color = Color.White)
                Text(
                    text = "Rp. 5.000",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier,
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                ) {
                    Text(text = "Tukar Koin", color = hijau40)
                }
            }
        }
    }
}

@Composable
fun TopProductsSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Top Produk Hasil", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = { /* TODO: Implement action */ }) {
                Text(text = "Lihat Selengkapnya")
            }
        }
//        LazyRow {
//            items(listOf("Lilin Aroma", "Lilin Aroma", "Lilin Aroma")) { product ->
//                TopProductItem(product)
//            }
//        }
    }
}

@Composable
fun TopProductItem(name: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(120.dp)
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_product_image), // Replace with actual resource
//            contentDescription = "Product Image",
//            modifier = Modifier
//                .height(120.dp)
//                .fillMaxWidth(),
//            contentScale = ContentScale.Crop
//        )
        Text(
            text = name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}

@Composable
fun InformationSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Informasi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = { /* TODO: Implement action */ }) {
                Text(text = "Lihat Informasi lainnya")
            }
        }
//        Image(
//            painter = painterResource(id = R.drawable.ic_info_image), // Replace with actual resource
//            contentDescription = "Info Image",
//            modifier = Modifier
//                .height(120.dp)
//                .fillMaxWidth(),
//            contentScale = ContentScale.Crop
//        )
    }
}

@Composable
fun LatestReportsSection() {
    Column {
        Text(text = "Laporan Terkini", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        // Repeat the row as needed for each report
        ReportItem()
    }
}

@Composable
fun ReportItem() {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_report_image), // Replace with actual resource
//            contentDescription = "Report Image",
//            modifier = Modifier
//                .size(64.dp)
//                .clip(MaterialTheme.shapes.small)
//        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "Perkembangan Maggot di rumah sampah digital", fontWeight = FontWeight.Bold)
            Text(text = "Rabu, 17 Juli 2024", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ArticleSection(artikels:List<Article>) {
    Column {
        Text(text = "Artikel", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        ArticleItem()
    }
}

@Composable
fun ArticleItem() {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_article_image), // Replace with actual resource
//            contentDescription = "Article Image",
//            modifier = Modifier
//                .size(64.dp)
//                .clip(MaterialTheme.shapes.small)
//        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "Perkembangan Maggot di rumah sampah digital", fontWeight = FontWeight.Bold)
            Text(text = "Rabu, 17 Juli 2024", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomePage()
}