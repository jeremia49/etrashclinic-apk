package my.id.jeremia.etrash.fcm.core

import androidx.annotation.Keep
import kotlin.random.Random

interface Notification {
    suspend fun send()

    @Keep
    enum class Type(val value: Int) {
        UNKNOWN(0),
        TEXT(1),
        IMAGE(2),
        TEXT_AND_IMAGE(3),
        CONTENT(4);

        fun unique(): Int {
            return this.value * Random.nextInt(1, 1001)
        }

        companion object {
            fun parse(name: String) =
                when (name) {
                    "TEXT" -> TEXT
                    "IMAGE" -> IMAGE
                    "TEXT_AND_IMAGE" -> TEXT_AND_IMAGE
                    "CONTENT" -> CONTENT
                    else -> UNKNOWN
                }
        }
    }

    @Keep
    enum class Action(val requestCode: Int) {
        APP_OPEN(100),
    }
}