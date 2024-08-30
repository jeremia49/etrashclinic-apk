package my.id.jeremia.etrash.init

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import my.id.jeremia.etrash.BuildConfig
import my.id.jeremia.etrash.R
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.data.repository.UserRepository
import my.id.jeremia.potholetracker.init.Initializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseInit @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataRepository: DataRepository,
    private val userRepository: UserRepository,
) : Initializer {
    override fun init() {
        syncFCMToken()
        createDefaultNotificationChannel()
    }

    companion object {
        const val TAG = "FirebaseInit"
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun syncFCMToken() {
        if (!userRepository.isFirebaseTokenSent()
                .blockingFirst() && userRepository.getCurrentAuth() != null
        ) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e(
                        TAG,
                        "Fetching FCM registration token failed :" + task.exception.toString()
                    )
                    return@addOnCompleteListener
                }
                val token = task.result

                userRepository.setFirebaseToken(token).subscribe()

                GlobalScope.launch{
                    dataRepository.sendFCMToken(token)
                        .catch { }
                        .collect {
                            userRepository.setFirebaseTokenSent(true).subscribe()
                        }
                }

                if (BuildConfig.DEBUG) Log.d(TAG, "FCM Token :" + token)
            }
        }
    }

    private fun createDefaultNotificationChannel() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                val notificationManager =
                    context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        context.getString(R.string.default_notification_channel_id),
                        context.getString(R.string.notification_default_channel_name),
                        NotificationManager.IMPORTANCE_DEFAULT
                    ).apply {
                        description =
                            context.getString(R.string.notification_default_channel_description)
                    })

            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }
}

