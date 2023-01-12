package com.example.ration.di

import com.example.ration.calculate.CalculateRepository
import com.example.ration.calculate.CalculateViewModel
import com.example.ration.chooseProduct.ChooseProductViewModel
import com.example.ration.chooseProduct.ChooseRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val calculateRepoModule = module(override = true) {
    single { CalculateRepository() }
}
val chooseRepoModule = module(override = true) {
    single { ChooseRepository() }
}
val calculateViewModule = module(override = true) {
    viewModel { CalculateViewModel(get()) }
}
val chooseViewModule = module(override = true) {
    viewModel { ChooseProductViewModel(get()) }
}
