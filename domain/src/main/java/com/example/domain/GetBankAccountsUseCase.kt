package com.example.domain

import com.example.data.Bank

interface GetBankAccountsUseCase {
    suspend fun execute(): List<Bank>
}
