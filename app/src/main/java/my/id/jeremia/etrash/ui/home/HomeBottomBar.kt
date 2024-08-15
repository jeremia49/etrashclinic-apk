package my.id.jeremia.etrash.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.ui.navigation.Destination
import my.id.jeremia.etrash.ui.theme.EtrashTheme
import my.id.jeremia.etrash.ui.theme.hijau40


@Keep
enum class HomeTab(
    @StringRes val title: Int,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
    val route: String,
    val destRoute: String,
) {
    HOME(
        R.string.home_str,
        R.drawable.home_unselected,
        R.drawable.home_selected,
        Destination.Home.MyHome.route,
        Destination.Home.MyHome.route,
    ),

    UNGGAH(
        R.string.unggah_str,
        R.drawable.camera_unselected,
        R.drawable.camera_selected,
        Destination.Home.Camera.route,
        Destination.Home.Camera.route,
    ),

    RIWAYAT(
        R.string.riwayat_str,
        R.drawable.history_unselected,
        R.drawable.history_selected,
        Destination.Home.Riwayat.route,
        Destination.Home.Riwayat.route,
    ),

}

@Composable
fun HomeBottomBar(navController: NavController) {
    val tabs = remember { HomeTab.entries.toTypedArray().asList() }
    val routes = remember { HomeTab.entries.map { it.route } }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: Destination.Login.route

    HomeBottomBarView(
        tabs = tabs,
        routes = routes,
        currentRoute = currentRoute,
        tabClick = {
            if (it.route != currentRoute) {
                navController.navigate(it.destRoute) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    )
}

@Composable
private fun HomeBottomBarView(
    tabs: List<HomeTab>,
    routes: List<String>,
    currentRoute: String,
    tabClick: (HomeTab) -> Unit
) {

    if (currentRoute in routes) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xB3FFFFFF))
                .padding(horizontal = 50.dp)
                .windowInsetsBottomHeight(
                    WindowInsets.navigationBars.add(WindowInsets(bottom = 70.dp))
                )
            ,
            tonalElevation = 5.dp,
        ) {
            tabs.forEach { tab ->
                val selected = currentRoute == tab.route
                NavigationBarItem(
                    icon = {
                        Icon(
                            painterResource(if (selected) tab.selectedIcon else tab.unselectedIcon),
                            contentDescription = null,
                            tint = if (selected) {
                                hijau40
                            } else {
                                Color.Gray
                            }
                        )
                    },
                    label = {
                        Text(
                            stringResource(id = tab.title), color = if (selected) {
                                hijau40
                            } else {
                                Color.Gray
                            }
                        )
                    },
                    selected = selected,
                    onClick = { tabClick(tab) },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors().copy(
                        selectedIndicatorColor = Color.Transparent,
                        disabledIconColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .navigationBarsPadding(),
                )
            }
        }
    }
}

@Preview("Light")
@Composable
private fun HomeBottomBarLightPreview() {
    EtrashTheme {
        HomeBottomBarView(
            tabs = HomeTab.entries.toTypedArray().asList(),
            routes = HomeTab.entries.map { it.route },
            currentRoute = HomeTab.HOME.route,
        ) {}
    }
}

@Preview("Dark")
@Composable
private fun HomeBottomBarDarkPreview() {
    EtrashTheme(darkTheme = true) {
        HomeBottomBarView(
            tabs = HomeTab.entries.toTypedArray().asList(),
            routes = HomeTab.entries.map { it.route },
            currentRoute = HomeTab.HOME.route,
        ) {}
    }
}