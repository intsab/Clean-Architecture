package com.intsab.currencyconverter.di

import com.intsab.core.corenetwork.services.CurrencyService
import com.intsab.core.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by intsabhaider
 * on 29,April,2023
 */
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit):CurrencyService{
        return retrofit.create(CurrencyService::class.java)
    }
}