package my.id.jeremia.etrash.ui.uploadsampah

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage

@Composable
fun UploadSampahView(modifier: Modifier = Modifier, viewModel: UploadSampahViewModel) {
    BackgroundImage {
        UploadSampahPage(modifier)
    }
}

@Composable
fun UploadSampahPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

    }
}