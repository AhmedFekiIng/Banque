package com.example.domain

import com.example.domain.data.BankDataSource

class GetBankAccountsUseCaseImpl(private val bankDataSource: BankDataSource) : GetBankAccountsUseCase {
    override suspend fun execute(): List<com.example.data.Bank> {
        return bankDataSource.getBanks()
    }
}
