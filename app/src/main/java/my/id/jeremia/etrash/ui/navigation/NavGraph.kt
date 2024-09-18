package my.id.jeremia.etrash.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import my.id.jeremia.etrash.ui.camera.CameraView
import my.id.jeremia.etrash.ui.camera.CameraViewModel
import my.id.jeremia.etrash.ui.coinexchange.CoinExchangeView
import my.id.jeremia.etrash.ui.coinexchange.CoinExchangeViewModel
import my.id.jeremia.etrash.ui.homepage.HomePageView
import my.id.jeremia.etrash.ui.homepage.HomePageViewModel
import my.id.jeremia.etrash.ui.leaderboard.LeaderboardView
import my.id.jeremia.etrash.ui.leaderboard.LeaderboardViewModel
import my.id.jeremia.etrash.ui.leagues.LeaguesView
import my.id.jeremia.etrash.ui.login.Login
import my.id.jeremia.etrash.ui.login.LoginViewModel
import my.id.jeremia.etrash.ui.loginorregister.LoginOrRegister
import my.id.jeremia.etrash.ui.loginorregister.LoginOrRegisterViewModel
import my.id.jeremia.etrash.ui.notification.NotificationView
import my.id.jeremia.etrash.ui.notification.NotificationViewModel
import my.id.jeremia.etrash.ui.onboard.OnBoard
import my.id.jeremia.etrash.ui.onboard.OnBoardViewModel
import my.id.jeremia.etrash.ui.profile.ProfileView
import my.id.jeremia.etrash.ui.profile.ProfileViewModel
import my.id.jeremia.etrash.ui.register.Register
import my.id.jeremia.etrash.ui.register.RegisterViewModel
import my.id.jeremia.etrash.ui.riwayat.RiwayatView
import my.id.jeremia.etrash.ui.riwayat.RiwayatViewModel
import my.id.jeremia.etrash.ui.seemore.SeeMoreView
import my.id.jeremia.etrash.ui.seemore.SeeMoreViewModel
import my.id.jeremia.etrash.ui.settings.SettingsView
import my.id.jeremia.etrash.ui.settings.SettingsViewModel
import my.id.jeremia.etrash.ui.splash.Splash
import my.id.jeremia.etrash.ui.splash.SplashViewModel
import my.id.jeremia.etrash.ui.upload.UploadView
import my.id.jeremia.etrash.ui.upload.UploadViewModel
import my.id.jeremia.etrash.ui.uploadsampah.UploadSampahView
import my.id.jeremia.etrash.ui.uploadsampah.UploadSampahViewModel
import my.id.jeremia.etrash.ui.webview.WebView
import my.id.jeremia.etrash.ui.webview.WebViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Destination.Splash.route,
    navigator: Navigator,
    finish: () -> Unit = {},
) {
    NavHandler(
        navController = navController,
        navigator = navigator,
        finish = finish
    )
    
    NavHost(
        navController = navController,
        startDestination = startDestination,
    )
    {
        composable(Destination.Onboard.route) {
            val viewModel: OnBoardViewModel = hiltViewModel(key = SplashViewModel.TAG)
            OnBoard(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Splash.route) {
            val viewModel: SplashViewModel = hiltViewModel(key = SplashViewModel.TAG)
            Splash(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel(key = LoginViewModel.TAG)
            Login(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Register.route) {
            val viewModel: RegisterViewModel = hiltViewModel(key = RegisterViewModel.TAG)
            Register(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.LoginOrRegister.route) {
            val viewModel: LoginOrRegisterViewModel = hiltViewModel(key = RegisterViewModel.TAG)
            LoginOrRegister(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Home.MyHome.route) {
            val viewModel: HomePageViewModel = hiltViewModel(key = HomePageViewModel.TAG)
            HomePageView(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Home.Camera.route) {
            val viewModel: CameraViewModel = hiltViewModel(key = CameraViewModel.TAG)
            CameraView(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Home.Riwayat.route) {
            val viewModel: RiwayatViewModel = hiltViewModel(key = RiwayatViewModel.TAG)
            RiwayatView(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Home.Leaderboard.route) {
            val viewModel: LeaderboardViewModel = hiltViewModel(key = LeaderboardViewModel.TAG)
            LeaderboardView(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Home.Notification.route) {
            val viewModel: NotificationViewModel = hiltViewModel(key = NotificationViewModel.TAG)
            NotificationView(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Home.Settings.route) {
            val viewModel: SettingsViewModel = hiltViewModel(key = SettingsViewModel.TAG)
            SettingsView(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Home.CoinExchange.route) {
            val viewModel: CoinExchangeViewModel = hiltViewModel(key = CoinExchangeViewModel.TAG)
            CoinExchangeView(modifier = modifier, viewModel = viewModel)
        }

        composable(
            "${Destination.Home.WebView.route}/{safeurlbase64}",
            arguments = listOf(navArgument("safeurlbase64") {
                type = NavType.StringType
            })
        ) {
            val viewModel: WebViewModel = hiltViewModel(key = WebViewModel.TAG)
            WebView(
                modifier = modifier,
                viewModel = viewModel,
                content = it.arguments?.getString("safeurlbase64")!!
            )
        }

        composable(
            "${Destination.Home.SeeMore.route}/{jenis}",
            arguments = listOf(navArgument("jenis") {
                type = NavType.StringType
            })
        ) {
            val viewModel: SeeMoreViewModel = hiltViewModel(key = SeeMoreViewModel.TAG)
            SeeMoreView(
                modifier = modifier,
                viewModel = viewModel,
                jenis = it.arguments?.getString("jenis")!!
            )
        }

        composable(
            Destination.Home.Upload.route
        ) {
            val viewModel: UploadViewModel = hiltViewModel(key = UploadViewModel.TAG)
            UploadView(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Home.UploadSampah.route){
            val viewModel: UploadSampahViewModel = hiltViewModel(key = UploadSampahViewModel.TAG)
            UploadSampahView(modifier = modifier, viewModel = viewModel)
        }

        composable(Destination.Home.Leagues.route){
            LeaguesView(modifier = modifier)
        }

        composable(Destination.Home.Profile.route){
            val viewModel: ProfileViewModel = hiltViewModel(key = ProfileViewModel.TAG)
            ProfileView(modifier = modifier, viewModel=viewModel)
        }

    }


}