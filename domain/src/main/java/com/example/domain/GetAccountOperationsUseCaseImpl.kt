package com.example.domain

import com.example.domain.data.BankDataSource

class GetAccountOperationsUseCaseImpl(private val bankDataSource: BankDataSource) :
    GetAccountOperationsUseCase {
    override suspend fun execute(accountId: String): List<com.example.data.Operation> {
        val banks = bankDataSource.getBanks()
        val account = banks.flatMap { it.accounts }.find { it.id == accountId }
        return account?.operations ?: emptyList()
    }
}
