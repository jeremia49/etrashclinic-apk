package my.id.jeremia.etrash.ui.camera

import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

@Composable
fun CameraView(modifier: Modifier = Modifier, viewModel: CameraViewModel) {
    CameraPage(
        modifier = modifier,
        isLoading = viewModel.isLoading.collectAsStateWithLifecycle().value,
        onScannedAction = {
            viewModel.onScannedAction(result = it)
        }
    )
}


@Composable
fun CameraPage(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onScannedAction: (String) -> Unit = {}
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        AndroidView(
            factory = { context ->
                val view = CodeScannerView(context).apply {
                    autoFocusButtonColor = ContextCompat.getColor(context, android.R.color.white)
                    isAutoFocusButtonVisible = true
                    flashButtonColor = ContextCompat.getColor(context, android.R.color.white)
                    isFlashButtonVisible = true
                    frameColor = ContextCompat.getColor(context, android.R.color.white)
                    frameSize = 0.75f
                    frameThickness = 2
                    frameVerticalBias = 0.5f
                    maskColor = Color.parseColor("#77000000")
                    frameCornersSize = 50
                    frameCornersRadius = 50
                }

                val codeScanner = CodeScanner(context, view)
                codeScanner.camera =
                    CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
                codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,

                codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
                codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
                codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
                codeScanner.isFlashEnabled = false // Whether to enable flash or not

                // Callbacks
                codeScanner.decodeCallback = DecodeCallback {
                    val result = it.text
                    onScannedAction(result)
                }

                codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
                    Log.e("CameraView", "Camera initialization error: ${it.message}")
                }

                codeScanner.startPreview()

                view

            },
            modifier = Modifier
                .fillMaxSize(),
            update = {}
        )

    }
}


@Preview(showBackground = true)
@Composable
fun CameraScreenPreview() {
    CameraPage()
}