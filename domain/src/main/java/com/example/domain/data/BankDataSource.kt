package com.example.domain.data

import com.example.data.Bank

interface BankDataSource {
    suspend fun getBanks(): List<Bank>
}