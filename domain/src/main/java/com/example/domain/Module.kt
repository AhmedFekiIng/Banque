package com.example.domain

import com.example.data.createApiService
import com.example.domain.data.BankDataSource
import com.example.domain.data.BankRepository
import org.koin.dsl.module

val dataModule = module {
    single { createApiService() }
    single { BankRepository(get()) }
}


val domainModule = module {
    single<BankDataSource> { BankRepository(get()) }
    single<GetAccountOperationsUseCase> { GetAccountOperationsUseCaseImpl(get()) }
    single<GetBankAccountsUseCase> { GetBankAccountsUseCaseImpl(get()) }
}
