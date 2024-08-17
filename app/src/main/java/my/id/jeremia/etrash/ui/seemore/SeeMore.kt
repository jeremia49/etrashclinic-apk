package my.id.jeremia.etrash.ui.seemore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.id.jeremia.etrash.data.model.Article
import my.id.jeremia.etrash.data.model.Informasi
import my.id.jeremia.etrash.data.model.ProdukHasil
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.image.NetworkImageWithFilter
import my.id.jeremia.etrash.ui.homepage.ArticleItem
import my.id.jeremia.etrash.ui.homepage.TopProductItem
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.utils.common.toBase64UrlSafe

@Composable
fun SeeMoreView(modifier: Modifier = Modifier, jenis: String = "", viewModel: SeeMoreViewModel) {
    BackgroundImage {
        SeeMorePage(
            modifier = modifier,
            jenis = jenis,
            updateArtikel = {
                viewModel.updateArtikel()
            },
            updateProdukHasil = {
                viewModel.updateProdukHasil()
            },
            updateInformasi = {
                viewModel.updateInformasi()
            },
            artikels = viewModel.artikels.collectAsStateWithLifecycle().value,
            informasis = viewModel.informasis.collectAsStateWithLifecycle().value,
            produkHasils = viewModel.produkhasils.collectAsStateWithLifecycle().value,
            onClickOpenWebsite = { url ->
                viewModel.navigator.navigateTo(
                    "${Destination.Home.WebView.route}/${
                        toBase64UrlSafe(
                            url
                        )
                    }"
                )
            }
        )
    }
}

@Composable
fun SeeMorePage(
    modifier: Modifier = Modifier,
    jenis: String,
    updateInformasi: () -> Unit,
    updateProdukHasil: () -> Unit,
    updateArtikel: () -> Unit,
    artikels: List<Article>,
    informasis: List<Informasi>,
    produkHasils: List<ProdukHasil>,
    onClickOpenWebsite: (String) -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (jenis.contentEquals("produkhasil")) {
            LaunchedEffect(key1 = Unit) {
                updateProdukHasil()
            }
            Text(
                "Produk Hasil", fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 columns
                modifier = Modifier.fillMaxSize(),

                ) {
                repeat(produkHasils.size) { idx ->
                    item {
                        TopProductItem(produk = produkHasils.get(idx))
                    }
                }
            }
        } else if (jenis.equals("informasi")) {
            LaunchedEffect(key1 = Unit) {
                updateInformasi()
            }
            Text(
                "Informasi",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                repeat(informasis.size) { page ->
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
        } else if (jenis.contentEquals("artikel")) {
            LaunchedEffect(key1 = Unit) {
                updateArtikel()
            }
            Text(
                "Artikel",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                repeat(artikels.size) { index ->
                    ArticleItem(
                        artikels[index].title ?: "",
                        artikels[index].imgPublicUrl ?: "",
                        artikels[index].createdAt ?: "",
                        onClick = { onClickOpenWebsite(artikels[index].publicUrl!!) }
                    )
                }
            }
        }

    }
}