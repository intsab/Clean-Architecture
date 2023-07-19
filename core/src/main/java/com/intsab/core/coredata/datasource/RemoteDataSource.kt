package com.intsab.core.coredata.datasource

import com.intsab.core.coredata.datamodels.LatestRateParams
import com.intsab.core.corenetwork.datamodels.LatestRateResponse
import com.intsab.core.corenetwork.services.CurrencyService
import com.intsab.core.utils.Constants
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
class RemoteDataSource @Inject constructor(
    private val services: CurrencyService,
) {
    suspend fun getAllCurrencies(): Map<String, String> {
        return services.getCurrencies(
            url = String.format(Constants.currencies_endpoint, Constants.APP_ID)
        )
    }

    suspend fun getLatestRate(params: LatestRateParams): LatestRateResponse {
        return services.getLatestRate(
            url = String.format(Constants.latest_rate_endpoint, Constants.APP_ID, params.base)
        )
    }
}