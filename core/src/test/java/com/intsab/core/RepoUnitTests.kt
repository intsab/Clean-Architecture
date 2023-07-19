package com.intsab.core

import com.intsab.core.coredata.datamodels.LatestRateParams
import com.intsab.core.coredata.datasource.LocalDataSource
import com.intsab.core.coredata.datasource.RemoteDataSource
import com.intsab.core.coredata.repositries.CurrencyRepo
import com.intsab.core.corenetwork.datamodels.LatestRateResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Created by intsabhaider
 * on 01,May,2023
 */
class RepoUnitTests {
    @Test
    fun `getCurrencies with valid remote source call returns expected CurrencyResponseB`() =
        runBlocking {
            // Arrange
            val remoteSource = mockk<RemoteDataSource> {
                coEvery { getAllCurrencies() } returns getAllCurrencies()
            }
            val localSource = mockk<LocalDataSource>()
            val repo = CurrencyRepo(remoteSource, localSource)

            // Act
            val result = repo.getCurrencies()

            // Assert
            assertFalse(result.isError)
        }

    @Test
    fun `getCurrencies with remote source error returns expected CurrencyResponseB`() =
        runBlocking {
            // Arrange
            val remoteSource = mockk<RemoteDataSource> {
                coEvery { getAllCurrencies() } throws Exception("Some error")
            }
            val localSource = mockk<LocalDataSource>()
            val repo = CurrencyRepo(remoteSource, localSource)

            // Act
            val result = repo.getCurrencies()

            // Assert
            assertTrue(result.isError)
            assertTrue(result.currencies.isEmpty())
        }

    @Test
    fun `getLatestRates with valid remote source call returns expected LatestRateResponseB`() =
        runBlocking {
            // Arrange
            val remoteSource = mockk<RemoteDataSource> {
                coEvery { getLatestRate(any()) } returns LatestRateResponse(
                    "",
                    "",
                    "",
                    hashMapOf("" to 1.0, "" to 1.0, "" to 1.0),
                    "12222"
                )
            }
            val localSource = mockk<LocalDataSource>()
            val repo = CurrencyRepo(remoteSource, localSource)

            // Act
            val result = repo.getLatestRates(LatestRateParams("USD"))

            // Assert
            assertFalse(result.isError)
//            assertEquals(LatestRateResponse("", "", "",hashMapOf("" to 1.0, "" to 1.0, "" to 1.0),"122"), result.toModel())
        }

    @Test
    fun `getLatestRates with remote source error returns expected LatestRateResponseB`() =
        runBlocking {
            // Arrange
            val remoteSource = mockk<RemoteDataSource> {
                coEvery { getLatestRate(any()) } throws Exception("Some error")
            }
            val localSource = mockk<LocalDataSource>()
            val repo = CurrencyRepo(remoteSource, localSource)

            // Act
            val result = repo.getLatestRates(LatestRateParams("USD"))

            // Assert
            assertTrue(result.isError)
            assertTrue(result.rates.isEmpty())
        }




}