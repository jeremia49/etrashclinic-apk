package my.id.jeremia.etrash.utils.common

import android.util.Base64

fun toBase64UrlSafe(input: String): String {
    val byteArray = input.toByteArray(Charsets.UTF_8)
    val encoded = Base64.encodeToString(byteArray, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
    return encoded
}

fun fromBase64UrlSafe(encoded: String): String {
    val decodedBytes = Base64.decode(encoded, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
    return String(decodedBytes, Charsets.UTF_8)
}