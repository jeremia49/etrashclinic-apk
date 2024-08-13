package my.id.jeremia.etrash

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ETrashClinicApp : Application() {

    companion object{
        const val TAG="ETrashApp"
    }

    override fun onCreate() {
        super.onCreate()
    }
}