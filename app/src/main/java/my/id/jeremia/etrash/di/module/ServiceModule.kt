package my.id.jeremia.etrash.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import my.id.jeremia.etrash.di.qualifier.ScopeIO

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @Provides
    @ScopeIO
    @ServiceScoped
    fun provideIOCoroutineScope(dispatcher: Dispatchers) =
        CoroutineScope(dispatcher.IO + SupervisorJob())

}