package com.example.testmobile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetBankAccountsUseCase
import com.example.data.Bank
import com.example.data.createApiService
import com.example.domain.GetBankAccountsUseCaseImpl
import com.example.domain.data.BankRepository
import kotlinx.coroutines.launch

class AccountsListViewModel(private val getBankAccountsUseCase: GetBankAccountsUseCase) : ViewModel() {

    private val _bankAccounts = mutableStateOf<List<Bank>>(emptyList())
    val bankAccounts: State<List<Bank>> = _bankAccounts
    constructor() : this(GetBankAccountsUseCaseImpl(BankRepository(createApiService())))

    init {
        fetchBankAccounts()
    }

    private fun fetchBankAccounts() {
        Log.d("Loggg AccountsListViewModel", "Fetching bank accounts...")
        viewModelScope.launch {
            val accounts = getBankAccountsUseCase.execute()
            _bankAccounts.value = accounts
            Log.d("Loggg AccountsListViewModel", "Bank accounts fetched: ${accounts.size}")
        }
    }
}
