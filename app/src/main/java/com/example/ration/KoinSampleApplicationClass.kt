package com.example.ration

import android.app.Application
import com.example.ration.di.calculateRepoModule
import com.example.ration.di.calculateViewModule
import com.example.ration.di.chooseRepoModule
import com.example.ration.di.chooseViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class KoinSampleApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinSampleApplicationClass)
            androidLogger()
            modules(
                listOf(
                    calculateRepoModule, calculateViewModule, chooseRepoModule, chooseViewModule
                )
            )
        }
    }
}