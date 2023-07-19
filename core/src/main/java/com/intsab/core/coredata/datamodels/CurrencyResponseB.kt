package com.intsab.core.coredata.datamodels

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
data class CurrencyResponseB(val isError: Boolean, val currencies: ArrayList<CurrencyItemB>)
data class CurrencyItemB(val currencyName: String, val countryName: String)

fun Map<String, String>.toCurrencies(): ArrayList<CurrencyItemB> {
    val arrayList = arrayListOf<CurrencyItemB>()
    this.forEach {
        arrayList.add(CurrencyItemB(it.key, it.value))
    }
    return arrayList
}

fun Map<String, String>.toModel(): CurrencyResponseB {
    return CurrencyResponseB(false, this.toCurrencies())
}