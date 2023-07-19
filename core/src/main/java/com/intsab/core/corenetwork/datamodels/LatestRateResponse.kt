package com.intsab.core.corenetwork.datamodels

import com.google.gson.annotations.SerializedName


/**
 * Created by intsabhaider
 * on 26,April,2023
 */
data class LatestRateResponse(
    @SerializedName("base") val base: String,
    @SerializedName("disclaimer") val disclaimer: String,
    @SerializedName("license") val license: String,
    @SerializedName("rates") val rates: HashMap<String, Double>,
    @SerializedName("timestamp") val timestamp: String
)


