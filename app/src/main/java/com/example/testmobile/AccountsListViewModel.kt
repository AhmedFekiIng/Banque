package com.example.testmobile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetBankAccountsUseCase
import com.example.data.Bank
import kotlinx.coroutines.launch

class AccountsListViewModel(private val getBankAccountsUseCase: GetBankAccountsUseCase) : ViewModel() {

    private val _bankAccounts = mutableStateOf<List<com.example.data.Bank>>(emptyList())
    val bankAccounts: State<List<com.example.data.Bank>> = _bankAccounts

    init {
        fetchBankAccounts()
    }

    private fun fetchBankAccounts() {
        viewModelScope.launch {
            val accounts = getBankAccountsUseCase.execute()
            _bankAccounts.value = accounts
        }
    }
}
