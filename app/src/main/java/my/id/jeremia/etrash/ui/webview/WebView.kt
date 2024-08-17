package my.id.jeremia.etrash.ui.webview

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings.LOAD_DEFAULT
import android.webkit.WebSettings.LOAD_NORMAL
import android.webkit.WebSettings.LOAD_NO_CACHE
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kevinnzou.web.AccompanistWebChromeClient
import com.kevinnzou.web.AccompanistWebViewClient
import my.id.jeremia.etrash.utils.common.fromBase64UrlSafe

@Composable
fun WebView(modifier: Modifier = Modifier, content: String = "", viewModel: WebViewModel) {
    AndroidView(
        factory = {
            android.webkit.WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                webChromeClient = WebChromeClient()
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.useWideViewPort = true
                settings.cacheMode = LOAD_NO_CACHE

            }
        },
        modifier = modifier,
        update = {
            it.loadUrl(fromBase64UrlSafe(content))
        }
    )
}