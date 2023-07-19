package com.intsab.core.coredomain

import com.intsab.core.coredata.datamodels.LatestRateParams
import com.intsab.core.coredata.datamodels.LatestRateResponseB
import com.intsab.core.coredata.repositries.CurrencyRepo
import com.intsab.core.utils.UseCase
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
class GetLatestRateUseCase @Inject constructor(
    private val repo: CurrencyRepo
) : UseCase<LatestRateResponseB, LatestRateParams>() {
    private val TAG = this::class.java.simpleName

    override suspend fun run(params: LatestRateParams?): LatestRateResponseB {
        return repo.getLatestRates(params!!)
    }
}