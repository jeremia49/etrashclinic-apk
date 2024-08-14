package my.id.jeremia.etrash.data.remote.utils

import okio.IOException

class NoConnectivityErr : IOException() {
    override val message: String = "No internet connection"
}