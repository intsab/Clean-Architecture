package com.intsab.currencyconverter

//import com.intsab.core.room.CurrencyDatabase
import com.intsab.core.room.CurrencyDatabase
import com.intsab.currencyconverter.di.AppComponent
import com.intsab.currencyconverter.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlinx.coroutines.DelicateCoroutinesApi

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
class CurrencyApp : DaggerApplication() {
    var appComponent: AppComponent? = null

    @DelicateCoroutinesApi
    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.factory().create(this)
        }
        return appComponent as AppComponent
    }
}