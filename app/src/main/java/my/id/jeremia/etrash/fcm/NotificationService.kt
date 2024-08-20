package my.id.jeremia.etrash.fcm

import com.google.firebase.messaging.FirebaseMessagingService

class NotificationService : FirebaseMessagingService(){
    override fun onNewToken(token: String) {
        super.onNewToken(token)

//        scope.launch {
//            if (userRepository.userExists()) {
//                userRepository.sendFirebaseToken(token)
//                    .catch { }
//                    .collect {
//                        userRepository.setFirebaseToken(it)
//                    }
//            }
//        }

    }
}

