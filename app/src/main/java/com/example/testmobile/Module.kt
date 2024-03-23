package com.example.testmobile

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AccountsListViewModel(get()) }
    viewModel { AccountOperationsViewModel(get()) }
}
