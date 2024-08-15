package my.id.jeremia.etrash.data.local.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import my.id.jeremia.etrash.di.qualifier.OnBoardDataStore
import javax.inject.Inject


class OnBoardDataStore @Inject constructor(
    @OnBoardDataStore private val dataStore: RxDataStore<Preferences>
) {
    private val ONBOARD_KEY = booleanPreferencesKey("onboard_setting")

    @OptIn(ExperimentalCoroutinesApi::class)
    fun isCompleted(): Flowable<Boolean> {
        return dataStore.data().map {
            it[ONBOARD_KEY] ?: false
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun setOnBoard(onBoardStatus: Boolean) {
        dataStore.updateDataAsync {
            val pref = it.toMutablePreferences()
            pref[ONBOARD_KEY] = onBoardStatus
            Single.just(pref)
        }
    }


}