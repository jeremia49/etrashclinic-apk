package my.id.jeremia.etrash.ui.webview

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings.LOAD_NO_CACHE
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
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