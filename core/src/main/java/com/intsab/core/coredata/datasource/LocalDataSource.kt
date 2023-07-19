package com.intsab.core.coredata.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.intsab.core.corenetwork.datamodels.LatestRateResponse
import com.intsab.core.room.AppDatabase
import com.intsab.core.room.CurrencyDatabase
import com.intsab.core.utils.GsonExt.fromJson
import com.intsab.core.utils.GsonExt.toJson
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
class LocalDataSource @Inject constructor(private val context: Context) {
    private val dao = CurrencyDatabase.currencyDao()

    suspend fun updateLatestData(type: String, response: LatestRateResponse): LatestRateResponse? {
        if (response.rates.isNotEmpty()) {
            val json = toJson(response)
            dao?.updateData(type, json)
        }
        return getLatestData(type)
    }

    suspend fun getLatestData(type: String): LatestRateResponse? {
        return fromJson(
            dao?.fetchData(type) ?: "", LatestRateResponse::class.java
        )
    }

    suspend fun updateCurrencyData(type: String, response:  Map<String, String>): Map<String, String> {
        if (response.isNotEmpty()) {
            val json = toJson(response)
            dao?.updateData(type, json)
        }
        return getCurrencyData(type)
    }

    suspend fun getCurrencyData(type: String): Map<String, String> {
        val dataJson = dao?.fetchData(type) ?: ""
        if (dataJson.isNotEmpty()) {
            val mapType = object : TypeToken<Map<String, String>>() {}.type
            return Gson().fromJson(dataJson, mapType)
        } else {
            return mapOf<String, String>()
        }
    }
}