package com.example.domain.data

import com.example.data.Bank

class BankRepository(private val apiService: com.example.data.ApiService) : BankDataSource {
    override suspend fun getBanks(): List<Bank> {
        return apiService.getBanks()
    }
}
