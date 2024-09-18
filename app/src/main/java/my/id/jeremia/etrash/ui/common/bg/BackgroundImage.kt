package my.id.jeremia.etrash.ui.common.bg

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import my.id.jeremia.etrash.R

@Composable
fun BackgroundImage(
    modifier: Modifier = Modifier,
    showImage:Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {

    val context = LocalContext.current
    val bitmap by remember {
        mutableStateOf(
            BitmapFactory.decodeResource(
                context.resources,
                R.drawable.mainbg
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        if(showImage)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            content = content
        )
    }
}