package com.example.domain

import com.example.domain.data.BankRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BankRepositoryTest {

    @Mock
    private lateinit var mockApiService: com.example.data.ApiService

    private lateinit var bankRepository: BankRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        bankRepository = BankRepository(mockApiService)
    }

    @Test
    fun testGetBanks() = runBlocking {
        val banks = listOf(
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
            ),
            com.example.data.Bank(
                name = "Boursorama",
                isCA = 0,
                accounts = listOf(
                    com.example.data.BankAccount(
                        order = 2,
                        id = "09878900000",
                        holder = "Corinne Martin",
                        role = 1,
                        contractNumber = "32216549871",
                        label = "Compte de dépôt",
                        productCode = "00004",
                        balance = 45.84,
                        operations = listOf(
                            com.example.data.Operation(
                                id = "2",
                                title = "Tenue de compte",
                                amount = "-1,99",
                                category = "costs",
                                date = "1588690878"
                            ),
                            com.example.data.Operation(
                                id = "3",
                                title = "Tenue de compte",
                                amount = "-1,99",
                                category = "costs",
                                date = "1641760369"
                            )
                        )
                    )
                )
            )
        )

        Mockito.`when`(mockApiService.getBanks()).thenReturn(banks)

        val result = bankRepository.getBanks()
        assertEquals(banks, result)
    }
}
