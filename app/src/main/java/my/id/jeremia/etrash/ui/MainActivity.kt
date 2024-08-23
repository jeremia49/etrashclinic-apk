package my.id.jeremia.etrash.ui

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import my.id.jeremia.etrash.R

@AndroidEntryPoint()
class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>()

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                ContextCompat.getColor(this, R.color.black)
            )
        )

        setContent {
            ETrashMain(
                navigator = viewModel.navigator,
                loader = viewModel.loader,
                messenger = viewModel.messenger
            ) {
                finish()
            }
        }
    }
}
