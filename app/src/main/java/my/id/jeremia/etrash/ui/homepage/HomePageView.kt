package my.id.jeremia.etrash.ui.homepage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.data.model.Article
import my.id.jeremia.etrash.data.model.Informasi
import my.id.jeremia.etrash.data.model.Me
import my.id.jeremia.etrash.data.model.ProdukHasil
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.header.HeaderSection
import my.id.jeremia.etrash.ui.common.image.NetworkImage
import my.id.jeremia.etrash.ui.common.image.NetworkImageWithFilter
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.theme.hijau40
import my.id.jeremia.etrash.utils.common.toBase64UrlSafe
import my.id.jeremia.potholetracker.utils.common.CalendarUtils.getDateFromString
import my.id.jeremia.potholetracker.utils.common.CalendarUtils.getFormattedDateTime
import kotlin.math.min

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
            onClickProfilePicture = { viewModel.navigator.navigateTo(Destination.Home.Settings.route) },
            artikels = viewModel.artikels.collectAsStateWithLifecycle().value,
            informasis = viewModel.informasis.collectAsStateWithLifecycle().value,
            produkHasils = viewModel.produkhasils.collectAsStateWithLifecycle().value,
            me = viewModel.me.collectAsStateWithLifecycle().value,
            onClickOpenWebsite = { url ->
                viewModel.navigator.navigateTo(
                    "${Destination.Home.WebView.route}/${
                        toBase64UrlSafe(
                            url
                        )
                    }"
                )
            },
            onClickMore = { tipe ->
                viewModel.navigator.navigateTo("${Destination.Home.SeeMore.route}/${tipe}")
            }
        )
    }

}

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    username: String = "Jeremia",
    photoUrl: String = "https://example.com/photo.jpg",
    onClickProfilePicture: () -> Unit = {},
    artikels: List<Article> = emptyList(),
    informasis: List<Informasi> = emptyList(),
    produkHasils: List<ProdukHasil> = emptyList(),
    onClickOpenWebsite: (url: String) -> Unit = {},
    onClickMore: (tipe: String) -> Unit = {},
    me: Me = Me()
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderSection(username, photoUrl, onClickProfilePicture = onClickProfilePicture)
        Spacer(modifier = Modifier.height(16.dp))
        CoinSection(me)
        Spacer(modifier = Modifier.height(16.dp))
        TopProductsSection(produkHasils = produkHasils, onClickMore = onClickMore)
        Spacer(modifier = Modifier.height(16.dp))
        InformationSection(informasis = informasis, onClickMore = onClickMore)
        Spacer(modifier = Modifier.height(16.dp))
        LatestReportsSection(artikels = artikels, onClickArticle = onClickOpenWebsite)
        Spacer(modifier = Modifier.height(16.dp))
        ArticleSection(
            artikels = artikels,
            onClickArticle = onClickOpenWebsite,
            onClickMore = onClickMore
        )
    }
}


@Composable
fun CoinSection(me: Me) {
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
                Text(text = "${me.balance} Coins", fontSize = 16.sp, color = Color.White)
                Text(
                    text = "Rp. ${me.balanceidr}",
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
fun TopProductsSection(produkHasils: List<ProdukHasil>, onClickMore: (tipe: String) -> Unit) {
    if (produkHasils.size >= 1)
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Top Produk Hasil", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                TextButton(onClick = {
                    onClickMore("produkhasil")
                }) {
                    Text(text = "Lihat Selengkapnya")
                }
            }
            LazyRow {
                repeat(min(5, produkHasils.size)) { idx ->
                    item {
                        TopProductItem(produk = produkHasils.get(idx))
                    }
                }
            }
        }
}

@Composable
fun TopProductItem(produk: ProdukHasil) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NetworkImage(
            url = produk.imgPublicUrl!!,
            contentDescription = "Gambar Produk Hasil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
        )
        Text(
            text = produk.title!!,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InformationSection(
    informasis: List<Informasi>,
    onClickOpenWebsite: (url: String) -> Unit = {},
    onClickMore: (tipe: String) -> Unit
) {
    if (informasis.size >= 1) {
        val pagerState = rememberPagerState(pageCount = {
            informasis.size
        })

        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Informasi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                TextButton(onClick = {
                    onClickMore("informasi")
                }) {
                    Text(text = "Lihat Informasi Lainnya")
                }
            }

            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onClickOpenWebsite(informasis[page].publicUrl!!)
                        }
                ) {
                    NetworkImageWithFilter(
                        url = informasis[page].imgPublicUrl!!,
                        contentDescription = "Gambar Informasi",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Text(
                        informasis[page].title!!,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }


            }


        }
    }
}

@Composable
fun LatestReportsSection(artikels: List<Article>, onClickArticle: (url: String) -> Unit = {}) {
    if (artikels.size > 1)
        Column {
            Text(text = "Laporan Terkini", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            ReportItem(artikels.get(0), onClickArticle)
        }
}

@Composable
fun ReportItem(artikel: Article, onClickArticle: (url: String) -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickArticle(artikel.publicUrl!!)
            }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NetworkImage(
            url = artikel.imgPublicUrl!!, contentDescription = "Gambar Artikel",
            modifier = Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = artikel.title!!, fontWeight = FontWeight.Bold)
            Row {
                Icon(Icons.Default.CalendarMonth, contentDescription = "Tanggal")
                Text(
                    text = getFormattedDateTime(getDateFromString(artikel.createdAt!!)!!)!!,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ArticleSection(
    modifier: Modifier = Modifier,
    artikels: List<Article>,
    onClickArticle: (url: String) -> Unit = {},
    onClickMore: (tipe: String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Artikel", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(onClick = {
                onClickMore("artikel")
            }) {
                Text(text = "Lihat Artikel Lainnya")
            }
        }
        repeat(min(5, artikels.size)) { index ->
            ArticleItem(
                artikels[index].title ?: "",
                artikels[index].imgPublicUrl ?: "",
                artikels[index].createdAt ?: "",
                onClick = { onClickArticle(artikels[index]!!.publicUrl!!) }
            )
        }
    }
}

@Composable
fun ArticleItem(title: String, thumbnailUrl: String, tanggal: String, onClick: () -> Unit = {}) {
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
fun HomeScreenPreview() {
    HomePage()
}