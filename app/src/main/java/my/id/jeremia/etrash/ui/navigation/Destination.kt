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

        data object Camera:Screen("/home/camera")

        data object Riwayat:Screen("/home/riwayat")

        data object Leaderboard:Screen("/home/leaderboard")

        data object Notification:Screen("/home/notification")

        data object Settings:Screen("/home/settings")

        data object CoinExchange:Screen("/home/coin-exchange")

        data object MoreInformasi:Screen("/home/more-informasi")

        data object ProdukHasil:Screen("/home/produk-hasil")

        data object WebView:Screen("/home/web-view")

        data object SeeMore:Screen("/home/see-more")

    }

    abstract class Screen(baseRoute: String) {
        open val route = baseRoute
    }


}