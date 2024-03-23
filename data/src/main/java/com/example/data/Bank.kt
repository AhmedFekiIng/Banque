package com.example.data

data class Bank(
    val name: String,
    val isCA: Int,
    val accounts: List<BankAccount>
)