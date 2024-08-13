package my.id.jeremia.etrash.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import my.id.jeremia.etrash.ui.login.Login
import my.id.jeremia.etrash.ui.login.LoginViewModel
import my.id.jeremia.etrash.ui.loginorregister.LoginOrRegister
import my.id.jeremia.etrash.ui.loginorregister.LoginOrRegisterViewModel
import my.id.jeremia.etrash.ui.onboard.OnBoard
import my.id.jeremia.etrash.ui.onboard.OnBoardViewModel
import my.id.jeremia.etrash.ui.register.Register
import my.id.jeremia.etrash.ui.register.RegisterViewModel
import my.id.jeremia.etrash.ui.splash.Splash
import my.id.jeremia.etrash.ui.splash.SplashViewModel

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

        composable(Destination.LoginOrRegister.route){
            val viewModel: LoginOrRegisterViewModel = hiltViewModel(key = RegisterViewModel.TAG)
            LoginOrRegister(modifier = modifier, viewModel = viewModel)
        }

        navigation(
            route = Destination.Home.route,
            startDestination = Destination.Home.Find.route
        ) {


        }


    }


}