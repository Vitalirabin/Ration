package com.example.ration.di

import androidx.room.Room
import com.example.ration.calculate.CalculateRepository
import com.example.ration.calculate.CalculateViewModel
import com.example.ration.data_base.ProductDataBase
import com.example.ration.data_base.RationDataBase
import com.example.ration.delete.DeleteRepository
import com.example.ration.delete.DeleteViewModel
import com.example.ration.ration.RationRepository
import com.example.ration.ration.RationUseCase
import com.example.ration.ration.RationViewModel
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
val rationViewModule = module(override = true) {
    viewModel { RationViewModel(get()) }
}
val rationRepoModule = module(override = true) {
    single { RationRepository(get(), get()) }
}
val rationUseCaseModule = module(override = true) {
    single { RationUseCase(get()) }
}

val deleteViewModule = module(override = true) {
    viewModel { DeleteViewModel(get()) }
}
val deleteRepoModule = module(override = true) {
    single { DeleteRepository(get()) }
}

val rationDataBaseModule = module(override = true) {
    single {
        Room.databaseBuilder(androidApplication(), RationDataBase::class.java, "DataBase")
            .allowMainThreadQueries().build()
    }
}