package com.example.ration.di

import com.example.ration.CalculateRepository
import com.example.ration.CalculateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val calculateRepoModule = module(override = true) {
    single { CalculateRepository() }
}
val calculateViewModule = module(override = true) {
    viewModel { CalculateViewModel(get()) }
}

