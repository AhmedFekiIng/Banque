package com.example.testmobile

import android.os.Bundle
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
import com.example.domain.dataModule
import com.example.domain.domainModule
import com.example.testmobile.theme.BankAppTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    //private val viewModel: AccountsListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(dataModule, domainModule, appModule))
        }

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
                    AccountsListFragment(navController)
                }
                composable("accountOperationsScreen/{accountId}") { backStackEntry ->
                    val accountId = backStackEntry.arguments?.getString("accountId") ?: ""
                    AccountOperationsFragment(accountId)
                }
            }
        }
    }
}