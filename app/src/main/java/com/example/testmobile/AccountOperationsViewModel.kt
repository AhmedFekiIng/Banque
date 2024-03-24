package com.example.testmobile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.createApiService
import com.example.domain.GetAccountOperationsUseCase
import com.example.domain.GetAccountOperationsUseCaseImpl
import com.example.domain.data.BankRepository
import kotlinx.coroutines.launch

class AccountOperationsViewModel(private val getAccountOperationsUseCase: GetAccountOperationsUseCase) : ViewModel() {

    constructor() : this(GetAccountOperationsUseCaseImpl(BankRepository(createApiService())))

    val accountOperations = mutableStateOf<List<com.example.data.Operation>>(emptyList())

    fun fetchAccountOperations(accountId: String) {
        viewModelScope.launch {
            val operations = getAccountOperationsUseCase.execute(accountId)
                .sortedWith(compareByDescending<com.example.data.Operation> { it.date }.thenBy { it.title })
            accountOperations.value = sortOperations(operations)
        }
    }

    private fun sortOperations(operations: List<com.example.data.Operation>): List<com.example.data.Operation> {
        return operations.sortedWith(compareByDescending<com.example.data.Operation> { it.date }.thenBy { it.title })
    }
}
