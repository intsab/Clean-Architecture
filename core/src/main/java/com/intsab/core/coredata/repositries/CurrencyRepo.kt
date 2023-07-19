package com.intsab.core.coredata.repositries

import com.intsab.core.coredata.datamodels.CurrencyResponseB
import com.intsab.core.coredata.datamodels.LatestRateParams
import com.intsab.core.coredata.datamodels.LatestRateResponseB
import com.intsab.core.coredata.datamodels.toModel
import com.intsab.core.coredata.datasource.LocalDataSource
import com.intsab.core.coredata.datasource.RemoteDataSource
import com.intsab.core.room.CurrencyDatabase
import com.intsab.core.room.CurrencyDatabase.TYPE_LATEST
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
class CurrencyRepo @Inject constructor(
    private val remoteSource: RemoteDataSource,
    private val localSource: LocalDataSource,
) {
    suspend fun getCurrencies(params: String? = null): CurrencyResponseB {
        val response = localSource.getCurrencyData(CurrencyDatabase.TYPE_CURRENCY)
        if (response.isEmpty()) {
            return try {
                val remoteResponse = remoteSource.getAllCurrencies()
                localSource.updateCurrencyData(
                    CurrencyDatabase.TYPE_CURRENCY, remoteResponse
                )
                remoteResponse.toModel()
            } catch (e: Exception) {
                CurrencyResponseB(isError = true, arrayListOf())
            }
        } else {
            return response.toModel()
        }
    }

    suspend fun getLatestRates(params: LatestRateParams): LatestRateResponseB {
        val response = localSource.getLatestData(TYPE_LATEST)
        if (response == null || response.rates.isEmpty()) {
            return try {
                val remoteResponse = remoteSource.getLatestRate(params)
                localSource.updateLatestData(TYPE_LATEST, remoteResponse)

                remoteResponse.toModel()
            } catch (e: Exception) {
                LatestRateResponseB(true, "", "", arrayListOf())
            }
        } else {
            return response.toModel()
        }

    }

}