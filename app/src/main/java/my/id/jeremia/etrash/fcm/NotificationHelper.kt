package my.id.jeremia.etrash.fcm

import coil.ImageLoader
import my.id.jeremia.etrash.fcm.core.Notification
import my.id.jeremia.etrash.fcm.core.Payload
import my.id.jeremia.etrash.fcm.core.Provider
import my.id.jeremia.etrash.fcm.notifications.TextNotification
import javax.inject.Inject

class NotificationHelper @Inject constructor(
    private val imageLoader: ImageLoader,
    private val provider: Provider
) {


    suspend fun showNotification(payload: Payload) {
        when (payload.type) {
            Notification.Type.TEXT ->
                TextNotification(provider, payload).send()

            Notification.Type.IMAGE -> {}
            Notification.Type.CONTENT -> {}
            Notification.Type.TEXT_AND_IMAGE -> {}
            Notification.Type.UNKNOWN -> {}
        }
    }

    suspend fun showMessage(title:String, messageBody: String) {
        val payload = Payload(
            type = Notification.Type.TEXT,
            ticker = provider.defaults.ticker,
            subtitle = messageBody,
            title = title,
        )
        TextNotification(provider, payload).send()
    }
}