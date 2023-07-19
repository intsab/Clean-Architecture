package com.intsab.core.coredata.datamodels

import com.intsab.core.corenetwork.datamodels.LatestRateResponse

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
data class LatestRateResponseB(
    val isError: Boolean, val time: String, val base: String, val rates: ArrayList<RateB>
)

data class RateB(val currency: String, val rate: Double, var qty: Double = 1.0)
data class LatestRateParams(val base: String)

fun LatestRateResponse.toModel(): LatestRateResponseB {
    val rates = arrayListOf<RateB>()
    this.rates.forEach {
        rates.add(RateB(it.key, it.value))
    }
    return LatestRateResponseB(false, this.timestamp, this.base, rates)
}