package my.id.jeremia.etrash.data.repository

import my.id.jeremia.etrash.data.local.datastore.UserDataStore
import my.id.jeremia.etrash.data.model.Auth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    val userDataStore: UserDataStore,
) {

    fun saveCurrentAuth(auth: Auth) = userDataStore.setUserData(
        auth.userid, auth.username, auth.email, auth.nohp, auth.accessToken, auth.photoUrl
    )

    fun removeCurrentUser() =
        userDataStore.removeCurrentUser();

    fun getCurrentAuth(): Auth? {
        val userid = userDataStore.getUserId().blockingFirst()
        val email = userDataStore.getUserEmail().blockingFirst()
        val username = userDataStore.getUserName().blockingFirst()
        val nohp = userDataStore.getUserNohp().blockingFirst()
        val accesstoken = userDataStore.getAccessToken().blockingFirst()
        val photoUrl = userDataStore.getUserPhotoUrl().blockingFirst()

        if (userid.isNotEmpty()) {
            return Auth(
                userid,
                username!!,
                email!!,
                nohp!!,
                accesstoken!!,
                photoUrl
            )
        }

        return null
    }

    fun getCurrentAccessToken(): String {
        return userDataStore.getAccessToken().blockingFirst()
    }

}