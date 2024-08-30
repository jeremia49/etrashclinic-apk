package my.id.jeremia.etrash.fcm.core

import android.app.PendingIntent
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Action
import dagger.hilt.android.qualifiers.ApplicationContext
import my.id.jeremia.etrash.R
import javax.inject.Inject

class Provider @Inject constructor(@ApplicationContext val context: Context) {

    val pendingIntents = PendingIntents(context)

    val defaults = Defaults(
        channel = context.getString(R.string.default_notification_channel_id),
        ticker = context.getString(R.string.notification_new),
        color = context.getColor(R.color.teal_200),
        sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
        smallIcon = R.drawable.logo,
        largeIcon = R.drawable.logo,
        openAction = Action(
            R.drawable.ic_touch_app,
            context.getString(R.string.notification_open),
            pendingIntents.appOpen()
        )
    )

    fun buildOpenAction(pIntent: PendingIntent, label: String? = null): Action = Action(
        R.drawable.ic_touch_app,
        label ?: context.getString(R.string.notification_open),
        pIntent
    )


    fun basicNotificationBuilder() =
        NotificationCompat.Builder(context, defaults.channel)
            .setSmallIcon(defaults.smallIcon)
            .setLargeIcon((context.getDrawable(defaults.largeIcon) as BitmapDrawable).bitmap)
            .setSound(defaults.sound)
            .setDefaults(android.app.Notification.DEFAULT_ALL)
            .setColor(defaults.color)
            .setAutoCancel(true)

}