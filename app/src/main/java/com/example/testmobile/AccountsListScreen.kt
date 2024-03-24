package com.example.testmobile

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.Bank
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AccountsListScreen(navController: NavController, viewModel: AccountsListViewModel = viewModel()) {
    val banks = viewModel.bankAccounts.value
    Log.d("Loggg AccountsListScreen", "Number of banks: ${banks.size}")

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Comptes Crédit Agricole",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(banks.sortedBy { it.name }) { bank ->
            if (bank.isCA == 1) {
                BankItem(bank, navController)
            }
        }
        item {
            Text(
                text = "Autres comptes",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(banks.sortedBy { it.name }) { bank ->
            if (bank.isCA != 1) {
                BankItem(bank, navController)
            }
        }
    }
}

@Composable
fun BankItem(bank: Bank, navController: NavController) {
    val expanded = remember { mutableStateOf(false) }
    Log.d("Loggg BankItem", "Bank name: ${bank.name}")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {
                expanded.value = !expanded.value
            },
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = bank.name,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = if (expanded.value) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                    contentDescription = "Expand/Collapse",
                    tint = Color.Gray
                )
            }
            if (expanded.value) {
                bank.accounts.sortedByDescending { it.operations.maxByOrNull { it.date }?.date }
                    .forEach { account ->
                        BankAccountItem(account, navController)
                    }
            }
        }
    }
}

@Composable
fun BankAccountItem(account: com.example.data.BankAccount, navController: NavController) {
    Log.d("Loggg BankAccountItem", "Account label: ${account.label}")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {
                navController.navigate("accountOperationsScreen/${account.id}")
            },
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = account.label,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Balance: ${account.balance}",
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
        }
    }
}