package my.id.jeremia.etrash.ui.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomePageView(modifier : Modifier = Modifier, viewModel: HomePageViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(16.dp))
        CoinSection()
        Spacer(modifier = Modifier.height(16.dp))
        TopProductsSection()
        Spacer(modifier = Modifier.height(16.dp))
        InformationSection()
        Spacer(modifier = Modifier.height(16.dp))
        LatestReportsSection()
        Spacer(modifier = Modifier.height(16.dp))
        ArticleSection()
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Halo,", fontSize = 20.sp)
            Text(
                text = "Jeremia!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
//        Image(
//            painter = painterResource(id = R.drawable.ic_user_avatar), // Replace with actual resource
//            contentDescription = "User Avatar",
//            modifier = Modifier
//                .size(48.dp)
//                .clip(CircleShape)
//                .background(Color.LightGray)
//        )
    }
}

@Composable
fun CoinSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
//        colors = CardColors(
//            containerColor = Color(0xFF00BFA5),
//        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "5.000 Coins", fontSize = 20.sp, color = Color.White)
            Text(
                text = "Rp. 5.000",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* TODO: Implement action */ }) {
                Text(text = "Tukar Koin")
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
        LazyRow {
            items(listOf("Lilin Aroma", "Lilin Aroma", "Lilin Aroma")) { product ->
                TopProductItem(product)
            }
        }
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
fun ArticleSection() {
    Column {
        Text(text = "Artikel", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        // Repeat the row as needed for each article
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

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomePageView()
//}