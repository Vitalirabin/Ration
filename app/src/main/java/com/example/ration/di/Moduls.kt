package com.example.ration.di

import androidx.room.Room
import com.example.ration.calculate.CalculateRepository
import com.example.ration.calculate.CalculateViewModel
import com.example.ration.data_base.ProductDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val calculateRepoModule = module(override = true) {
    single { CalculateRepository(get()) }
}
val calculateViewModule = module(override = true) {
    viewModel { CalculateViewModel(get()) }
}
val productDataBaseModule = module(override = true) {
    single {
        Room.databaseBuilder(androidApplication(), ProductDataBase::class.java, "DB")
            .allowMainThreadQueries().build()
    }
}
