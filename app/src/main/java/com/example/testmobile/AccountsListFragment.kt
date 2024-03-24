package com.example.testmobile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.testmobile.theme.BankAppTheme
class AccountsListFragment(private val navController: NavController) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Loggg AccountsListFragment", "onCreateView called")
        return ComposeView(requireContext()).apply {
            setContent {
                BankAppTheme {
                    val viewModel: AccountsListViewModel = viewModel()
                    AccountsListScreen(navController, viewModel)
                }
            }
        }
    }
}