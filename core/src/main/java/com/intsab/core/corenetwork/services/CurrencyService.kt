package com.intsab.core.corenetwork.services

import com.intsab.core.corenetwork.datamodels.LatestRateResponse
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
interface CurrencyService {
    @GET
    suspend fun getLatestRate(
        @Url url: String
    ): LatestRateResponse

    @GET
    suspend fun getCurrencies(
        @Url url: String
    ): Map<String, String>

}