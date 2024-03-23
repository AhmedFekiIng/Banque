package com.example.domain

import com.example.data.Operation

interface GetAccountOperationsUseCase {
    suspend fun execute(accountId: String): List<Operation>
}
