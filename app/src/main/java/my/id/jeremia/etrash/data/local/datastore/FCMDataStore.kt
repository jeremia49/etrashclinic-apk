package my.id.jeremia.etrash.data.local.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import my.id.jeremia.etrash.di.qualifier.FCMDataStore
import javax.inject.Inject

class FCMDataStore @Inject constructor(
    @FCMDataStore private val dataStore: RxDataStore<Preferences>
) {
    private val FCMTOKEN_KEY = stringPreferencesKey("fcmtoken")
    private val FCMTOKEN_SENT_KEY = booleanPreferencesKey("fcmtoken_sent")

    @OptIn(ExperimentalCoroutinesApi::class)
    fun isFirebaseTokenSent(): Flowable<Boolean> {
        return dataStore.data().map {
            it[FCMTOKEN_SENT_KEY] ?: false
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getFirebaseToken(): Flowable<String> {
        return dataStore.data().map {
            it[FCMTOKEN_KEY] ?: ""
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun setFirebaseTokenSent(status: Boolean) =
        dataStore.updateDataAsync {
            val pref = it.toMutablePreferences()
            pref[FCMTOKEN_SENT_KEY] = status
            Single.just(pref)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun setFirebaseToken(token: String) =
        dataStore.updateDataAsync {
            val pref = it.toMutablePreferences()
            pref[FCMTOKEN_KEY] = token
            Single.just(pref)
        }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun removeFirebaseToken(): Single<Preferences> {
        return dataStore.updateDataAsync {
            val pref = it.toMutablePreferences()
            pref.clear()
            Single.just(pref)
        }
    }


}