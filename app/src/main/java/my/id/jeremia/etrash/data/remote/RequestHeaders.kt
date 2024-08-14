package my.id.jeremia.etrash.data.remote

import androidx.annotation.Keep
import javax.inject.Inject


class RequestHeaders @Inject constructor(){
    object Key {
        const val API_AUTH_TYPE = "API_AUTH_TYPE"
        const val AUTH_PUBLIC = "$API_AUTH_TYPE: public"
        const val AUTH_PROTECTED = "$API_AUTH_TYPE: protected"
    }

    @Keep
    enum class Type(val value: String) {
        PUBLIC("public"),
        PROTECTED("protected")
    }

    @Keep
    enum class Param(val value: String) {
        API_KEY("x-api-key"),
        DEVICE_ID("x-device-id"),
        ANDROID_VERSION("x-android-version"),
        TIMEZONE_OFFSET("x-timezone-offset"),
        ACCESS_TOKEN("Authorization")
    }
}