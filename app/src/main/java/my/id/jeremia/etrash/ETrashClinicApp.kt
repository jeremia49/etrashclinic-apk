package my.id.jeremia.etrash

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import my.id.jeremia.potholetracker.init.CoilInit
import javax.inject.Inject

@HiltAndroidApp
class ETrashClinicApp : Application() {

    companion object{
        const val TAG="ETrashApp"
    }

    @Inject
    lateinit var coilInit: CoilInit

    override fun onCreate() {
        super.onCreate()
        coilInit.init()
    }
}