package com.example.ration

import android.app.Application
import com.example.ration.di.*
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
                    calculateViewModule, productDataBaseModule, calculateRepoModule,
                    rationRepoModule, rationViewModule, rationUseCaseModule, deleteViewModule,
                    deleteRepoModule, rationDataBaseModule
                )
            )
        }
    }
}