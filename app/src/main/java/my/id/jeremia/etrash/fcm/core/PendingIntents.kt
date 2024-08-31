package my.id.jeremia.etrash.fcm.core

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import my.id.jeremia.etrash.ui.navigation.Destination

class PendingIntents(private val context:Context) {

    fun appOpen(): PendingIntent =
        buildDeeplinkPendingIntent(
            Notification.Action.APP_OPEN,
            Destination.Home.Notification.route
        )


    private fun buildDeeplinkPendingIntent(
        action: Notification.Action,
        deeplink: String
    ): PendingIntent =
        PendingIntent.getActivity(
            context,
            action.requestCode,
            Intent(Intent.ACTION_VIEW, Uri.parse(deeplink)),
            PendingIntent.FLAG_IMMUTABLE
        )


}