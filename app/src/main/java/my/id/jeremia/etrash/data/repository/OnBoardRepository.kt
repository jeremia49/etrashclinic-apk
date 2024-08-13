package my.id.jeremia.etrash.data.repository

import io.reactivex.rxjava3.core.Flowable
import my.id.jeremia.etrash.data.local.datastore.OnBoardDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnBoardRepository @Inject constructor(private val onBoardDataStore: OnBoardDataStore) {

    fun isCompleted(): Flowable<Boolean> = onBoardDataStore.isCompleted()

    fun setOnBoard(onBoardStatus: Boolean): Unit = onBoardDataStore.setOnBoard(onBoardStatus)
}
