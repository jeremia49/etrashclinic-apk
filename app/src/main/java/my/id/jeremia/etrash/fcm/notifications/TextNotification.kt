package my.id.jeremia.etrash.fcm.notifications

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import my.id.jeremia.etrash.fcm.core.Notification
import my.id.jeremia.etrash.fcm.core.Payload
import my.id.jeremia.etrash.fcm.core.Provider

class TextNotification(
    private val provider: Provider,
    private val payload: Payload
) : Notification {
    override suspend fun send() {
        try {
            val style = NotificationCompat.BigTextStyle()
                .bigText(payload.message)
                .setBigContentTitle(payload.title)

            val notificationBuilder = provider
                .basicNotificationBuilder()
                .setTicker(payload.ticker)
                .setContentTitle(payload.title)
                .setContentText(payload.subtitle)
                .setStyle(style)
                .setContentIntent(provider.pendingIntents.appOpen())
//                .addAction(provider.defaults.openAction)

            val notificationManager =
                provider.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(
                Notification.Type.TEXT.unique(),
                notificationBuilder.build()
            )

        } catch (e: Exception) {
            Log.d("TextNotification",e.message.toString())
        }
    }
}