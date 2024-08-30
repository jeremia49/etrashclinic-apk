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
import my.id.jeremia.etrash.di.qualifier.AuthDataStore
import my.id.jeremia.etrash.di.qualifier.FCMDataStore
import my.id.jeremia.etrash.di.qualifier.OnBoardDataStore
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    @OnBoardDataStore
    fun provideOnBoardDataStore(
        @ApplicationContext applicationContext: Context,
    ): RxDataStore<Preferences>{
        return RxPreferenceDataStoreBuilder(applicationContext, "onboard").build()
    }


    @Provides
    @Singleton
    @AuthDataStore
    fun provideAuthDataStore(
        @ApplicationContext applicationContext: Context,
    ): RxDataStore<Preferences>{
        return RxPreferenceDataStoreBuilder(applicationContext, "auth").build()
    }

    @Provides
    @Singleton
    @FCMDataStore
    fun provideFCMDataStore(
        @ApplicationContext applicationContext: Context,
    ): RxDataStore<Preferences>{
        return RxPreferenceDataStoreBuilder(applicationContext, "fcm").build()
    }

}