package my.id.jeremia.etrash.ui.common.image

import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun NetworkImageWithFilter(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String,
    contentScale: ContentScale,
) {
    val contrast = 1f // 0f..10f (1 should be default)
    val brightness = -90f // -255f..255f (0 should be default)
    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        modifier = modifier,
        contentDescription = null
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Error -> {
                // Error content
            }

            is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
            else -> Image(
                painter = painter,
                contentDescription = contentDescription,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                contentScale = contentScale
            )
        }
    }

}