package my.id.jeremia.etrash.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.data.repository.UserRepository
import my.id.jeremia.etrash.di.qualifier.ScopeIO
import javax.inject.Inject

class NotificationService : FirebaseMessagingService(){

    companion object {
        private const val TAG = "NotificationService"
    }

    @Inject
    @ScopeIO
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var dataRepository: DataRepository

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        scope.launch {
            if (userRepository.getCurrentAuth() != null) {
                userRepository.setFirebaseToken(token).subscribe()

                scope.launch{
                    dataRepository.sendFCMToken(token)
                        .catch { }
                        .collect {
                            userRepository.setFirebaseTokenSent(true).subscribe()
                        }
                }

            }
        }

    }
}

