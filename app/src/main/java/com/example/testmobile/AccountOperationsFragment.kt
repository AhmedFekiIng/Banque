package com.example.testmobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.data.Operation
import com.example.testmobile.theme.BankAppTheme

class AccountOperationsFragment(private val accountId: String) : Fragment() {
    private val viewModel: AccountOperationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                BankAppTheme {
                    AccountOperationsScreen(accountId, viewModel)
                }
            }
        }
    }
}

@Composable
fun AccountOperationsScreen(accountId: String, viewModel: AccountOperationsViewModel) {
    viewModel.fetchAccountOperations(accountId)
    val accountOperations = viewModel.accountOperations

    LazyColumn {
        items(accountOperations.value) { operation ->
            OperationItem(operation)
        }
    }
}

@Composable
fun OperationItem(operation: Operation) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = operation.title,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 18.sp,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Amount: ${operation.amount}",
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Category: ${operation.category}",
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Date: ${operation.date}",
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
        }
    }
}