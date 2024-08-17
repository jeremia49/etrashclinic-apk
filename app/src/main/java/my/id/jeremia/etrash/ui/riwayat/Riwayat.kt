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
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage

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

    }
}

@Preview(showBackground = true)
@Composable
fun RiwayatPreview() {
    RiwayatPage()
}