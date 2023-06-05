package com.akexorcist.jetpackcomposeexperiment

import android.app.Application
import com.akexorcist.jetpackcomposeexperiment.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ComposeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(applicationContext)
            modules(AppModule.modules)
        }
    }
}