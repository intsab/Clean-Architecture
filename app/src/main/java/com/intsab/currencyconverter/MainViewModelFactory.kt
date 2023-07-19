package com.intsab.currencyconverter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intsab.core.coredomain.GetCurrenciesUseCase
import com.intsab.core.coredomain.GetLatestRateUseCase
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 30,April,2023
 */
class MainViewModelFactory @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val getLatestRateUseCase: GetLatestRateUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCurrenciesUseCase, getLatestRateUseCase) as T
    }

}