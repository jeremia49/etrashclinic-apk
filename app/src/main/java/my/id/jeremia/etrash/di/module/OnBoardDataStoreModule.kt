package my.id.jeremia.etrash.di.module

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.RxDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OnBoardDataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext applicationContext: Context,
    ): RxDataStore<Preferences>{
        return RxPreferenceDataStoreBuilder(applicationContext, "datastore").build()
    }

}