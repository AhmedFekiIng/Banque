package com.example.testmobile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testmobile.theme.BankAppTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Loggg MainActivity", "onCreate called")
        setContent {
            BankApp()
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun BankApp() {
    BankAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "accountsListScreen") {
                composable("accountsListScreen") {
                    Log.d("Loggg AccountsListFragment", "called")
                    AccountsListScreen(navController)
                }
                composable("accountOperationsScreen/{accountId}") { backStackEntry ->
                    val accountId = backStackEntry.arguments?.getString("accountId") ?: ""
                    AccountOperationsScreen(accountId)
                }
            }
        }
    }
}