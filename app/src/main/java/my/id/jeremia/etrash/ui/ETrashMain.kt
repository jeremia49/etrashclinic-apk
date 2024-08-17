package my.id.jeremia.etrash.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import my.id.jeremia.etrash.ui.common.bottombar.HomeBottomBar
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.loader.Loading
import my.id.jeremia.etrash.ui.common.snackbar.AppSnackbar
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.navigation.NavGraph
import my.id.jeremia.etrash.ui.navigation.Navigator
import my.id.jeremia.etrash.ui.theme.EtrashTheme

@Composable
fun ETrashMain(
    navigator: Navigator,
    loader: Loader,
    messenger: Messenger,
    finish: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    EtrashTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.imePadding(),
            snackbarHost = { AppSnackbar(snackbarHostState, messenger) },
            bottomBar = { HomeBottomBar(navController = navController) },
        ) { innerPaddingModifier ->
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPaddingModifier),
                navigator = navigator,
                finish = finish
            )
            Loading(
                modifier = Modifier
                    .padding(innerPaddingModifier)
                    .fillMaxWidth(),
                loader = loader
            )
        }
    }
}