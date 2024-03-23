package com.example.testmobile

import android.os.Parcelable
import androidx.navigation.NavArgs
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountOperationsFragmentArgs(
    val accountId: String
) : NavArgs, Parcelable
