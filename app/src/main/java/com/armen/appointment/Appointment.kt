package com.armen.appointment

import android.app.Application
import com.armen.appointment.di.DIModules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Appointment: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)

            androidLogger(Level.NONE)

            modules(appModule)
        }
    }
}
