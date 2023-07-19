package com.intsab.currencyconverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intsab.core.coredata.datamodels.*
import com.intsab.core.coredomain.GetCurrenciesUseCase
import com.intsab.core.coredomain.GetLatestRateUseCase
import kotlinx.coroutines.launch

/**
 * Created by intsabhaider
 * on 27,April,2023
 */
class MainViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val getLatestRateUseCase: GetLatestRateUseCase
) : ViewModel() {
    val error = MutableLiveData<String>()

    private val _getCurrenciesLiveData = MutableLiveData<CurrencyResponseB>()
    val getCurrenciesLiveData: LiveData<CurrencyResponseB> = _getCurrenciesLiveData

    fun getCurrencies(): MainViewModel {
        viewModelScope.launch {
            val result = getCurrenciesUseCase.run(null)
            if (result.isError) {
                error.postValue("getCurrenciesExp")
            } else {
                _getCurrenciesLiveData.postValue(result)
            }

        }
        return this
    }

    private val _getLatestRateLiveData = MutableLiveData<LatestRateResponseB>()
    val getLatestRateLiveData: LiveData<LatestRateResponseB> = _getLatestRateLiveData

    fun getLatestRate(base: String): MainViewModel {
        viewModelScope.launch {
            val result = getLatestRateUseCase.run(LatestRateParams(base))
            if (result.isError) {
                error.postValue("getLatestRateExp")
            } else {
                _getLatestRateLiveData.postValue(result)
            }

        }
        return this
    }

}

