package my.id.jeremia.etrash.ui.riwayat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.id.jeremia.etrash.ui.camera.CameraPage
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage
import my.id.jeremia.etrash.ui.common.header.HeaderSection
import my.id.jeremia.etrash.ui.homepage.ArticleSection
import my.id.jeremia.etrash.ui.homepage.CoinSection
import my.id.jeremia.etrash.ui.homepage.InformationSection
import my.id.jeremia.etrash.ui.homepage.LatestReportsSection
import my.id.jeremia.etrash.ui.homepage.TopProductsSection

@Composable
fun RiwayatView(modifier: Modifier = Modifier, viewModel: RiwayatViewModel) {
    BackgroundImage {
        RiwayatPage(
            modifier = modifier,

            )
    }
}

@Composable
fun RiwayatPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//        HeaderSection(username, photoUrl)
        Spacer(modifier = Modifier.height(16.dp))
        CoinSection()
        Spacer(modifier = Modifier.height(16.dp))
        TopProductsSection()
        Spacer(modifier = Modifier.height(16.dp))
        InformationSection()
        Spacer(modifier = Modifier.height(16.dp))
        LatestReportsSection()
    }
}

@Preview(showBackground = true)
@Composable
fun RiwayatPreview() {
    RiwayatPage()
}