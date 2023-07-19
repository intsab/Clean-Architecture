package com.intsab.core.coredomain

import com.intsab.core.coredata.datamodels.CurrencyResponseB
import com.intsab.core.coredata.repositries.CurrencyRepo
import com.intsab.core.utils.UseCase
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
class GetCurrenciesUseCase @Inject constructor(
    private val repo: CurrencyRepo
) : UseCase<CurrencyResponseB, String>() {
    private val TAG = this::class.java.simpleName

    override suspend fun run(params: String?): CurrencyResponseB {
        return repo.getCurrencies(params)
    }
}