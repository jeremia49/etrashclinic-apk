package my.id.jeremia.etrash.ui.navigation


object Destination {

    data object Onboard : Screen("/onboard")

    data object Splash : Screen("/splash")

    data object Login : Screen("/login")

    data object Register: Screen("/register")

    data object LoginOrRegister : Screen("/login-or-register")

    data object ServerUnreachable: Screen("/server-unreachable")

    data object Home:Screen("/home")
    {
        data object MyHome:Screen("/home/myhome")

        data object MyAccount:Screen("/home/myaccount")
    }

    abstract class Screen(baseRoute: String) {
        open val route = baseRoute
    }


}