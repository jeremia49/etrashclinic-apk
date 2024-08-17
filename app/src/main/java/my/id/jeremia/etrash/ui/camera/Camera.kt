package my.id.jeremia.etrash.ui.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import my.id.jeremia.etrash.ui.common.bg.BackgroundImage

@Composable
fun CameraView(modifier: Modifier = Modifier, viewModel: CameraViewModel) {
    BackgroundImage {
        CameraPage(
            modifier = modifier,

        )
    }
}


@Composable
fun CameraPage(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun CameraScreenPreview() {
    CameraPage()
}