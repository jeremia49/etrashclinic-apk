package my.id.jeremia.etrash.data.local.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import my.id.jeremia.etrash.di.qualifier.AuthDataStore
import javax.inject.Inject

class UserDataStore @Inject constructor(
    @AuthDataStore private val dataStore: RxDataStore<Preferences>
) {

    companion object {
        private val USER_ID = stringPreferencesKey("PREF_KEY_USER_ID")
        private val USER_NAME = stringPreferencesKey("PREF_KEY_USER_NAME")
        private val USER_EMAIL = stringPreferencesKey("PREF_KEY_USER_EMAIL")
        private val USER_NOHP = stringPreferencesKey("PREF_KEY_USER_NOHP")
        private val USER_PHOTOURL = stringPreferencesKey("PREF_KEY_USER_PHOTOURL")

        private val ACCESS_TOKEN = stringPreferencesKey("PREF_KEY_ACCESS_TOKEN")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUserId() : Flowable<String> = dataStore.data().map {
        it[USER_ID] ?: ""
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUserName() : Flowable<String> = dataStore.data().map {
        it[USER_NAME] ?: ""
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUserEmail() : Flowable<String> = dataStore.data().map {
        it[USER_EMAIL] ?: ""
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUserNohp() : Flowable<String> = dataStore.data().map {
        it[USER_NOHP] ?: ""
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAccessToken() : Flowable<String> = dataStore.data().map {
        it[ACCESS_TOKEN] ?: ""
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUserPhotoUrl() : Flowable<String> = dataStore.data().map {
        it[USER_PHOTOURL] ?: ""
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun removeCurrentUser(): Single<Preferences> {
        return dataStore.updateDataAsync {
                val pref = it.toMutablePreferences()
                pref.clear()
                Single.just(pref)
            }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUserData(id:String, name:String, emai:String, nohp:String, token:String, photoUrl:String): Single<Preferences> {
        return dataStore.updateDataAsync {
            val pref = it.toMutablePreferences()
            pref[USER_ID] = id
            pref[USER_NAME] = name
            pref[USER_EMAIL] = emai
            pref[USER_NOHP] = nohp
            pref[ACCESS_TOKEN] = token
            pref[USER_PHOTOURL] = photoUrl
            Single.just(pref)
        }
    }


}