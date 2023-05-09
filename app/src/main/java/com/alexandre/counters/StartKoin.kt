package com.alexandre.counters

import android.app.Application
import com.alexandre.counters.di.appModule
import com.alexandre.counters.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class StartKoinAp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@StartKoinAp)
            modules(listOf(appModule, databaseModule))
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}