package com.example.domain

import com.example.domain.data.BankRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAccountOperationsUseCaseImplTest {

    private lateinit var bankRepository: BankRepository
    private lateinit var getAccountOperationsUseCase: GetAccountOperationsUseCase

    @Before
    fun setUp() {
        bankRepository = mockk()
        getAccountOperationsUseCase = GetAccountOperationsUseCaseImpl(bankRepository)
    }

    @Test
    fun `test execute with valid accountId`() {
        runBlocking {
            val accountId = "151515151151"
            val expectedOperations = listOf(
                com.example.data.Operation(
                    id = "2",
                    title = "Prélèvement Netflix",
                    amount = "-15,99",
                    category = "leisure",
                    date = "1644870724"
                ),
                com.example.data.Operation(
                    id = "4",
                    title = "CB Amazon",
                    amount = "-95,99",
                    category = "online",
                    date = "1644611558"
                )
            )
            val mockBanks = mockBanks()
            coEvery { bankRepository.getBanks() } returns mockBanks

            val result = getAccountOperationsUseCase.execute(accountId)
            assertEquals(expectedOperations, result)
        }
    }

    @Test
    fun `test execute with invalid accountId`() {
        runBlocking {
            val accountId = "invalidId"
            val mockBanks = mockBanks()
            coEvery { bankRepository.getBanks() } returns mockBanks
            val result = getAccountOperationsUseCase.execute(accountId)
            assertEquals(emptyList<com.example.data.Operation>(), result)
        }
    }

    private fun mockBanks(): List<com.example.data.Bank> {
        return listOf(
            com.example.data.Bank(
                name = "CA Languedoc",
                isCA = 1,
                accounts = listOf(
                    com.example.data.BankAccount(
                        order = 1,
                        id = "151515151151",
                        holder = "Corinne Martin",
                        role = 1,
                        contractNumber = "32216549871",
                        label = "Compte de dépôt",
                        productCode = "00004",
                        balance = 2031.84,
                        operations = listOf(
                            com.example.data.Operation(
                                id = "2",
                                title = "Prélèvement Netflix",
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644870724"
                            ),
                            com.example.data.Operation(
                                id = "4",
                                title = "CB Amazon",
                                amount = "-95,99",
                                category = "online",
                                date = "1644611558"
                            )
                        )
                    )
                )
            )
        )
    }
}
