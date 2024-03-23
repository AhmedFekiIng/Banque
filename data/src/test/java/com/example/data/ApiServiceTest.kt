package com.example.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {

    @Mock
    private lateinit var mockApi: ApiService

    @Test
    fun `test getBanks() with success`() = runBlocking {
        // Given
        val expectedBanks = listOf(
            Bank(
                name = "CA Languedoc",
                isCA = 1,
                accounts = listOf(
                    BankAccount(
                        order = 1,
                        id = "151515151151",
                        holder = "Corinne Martin",
                        role = 1,
                        contractNumber = "32216549871",
                        label = "Compte de dépôt",
                        productCode = "00004",
                        balance = 2031.84,
                        operations = listOf(
                            Operation(
                                id = "2",
                                title = "Prélèvement Netflix",
                                amount = "-15,99",
                                category = "leisure",
                                date = "1644870724"
                            ),
                            Operation(
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
        `when`(mockApi.getBanks()).thenReturn(expectedBanks)

        // When
        val result = mockApi.getBanks()

        // Then
        assertEquals(expectedBanks, result)
    }

    @Test(expected = Exception::class)
    fun `test getBanks() with error`(): Unit = runBlocking {
        // Given
        `when`(mockApi.getBanks()).thenThrow(Exception("API Error"))

        // When
        mockApi.getBanks()

        // Then
        // Exception should be thrown
    }
}