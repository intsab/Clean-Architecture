package com.intsab.core.room

import android.annotation.SuppressLint
import android.content.Context
import com.intsab.core.room.entity.CurrencyEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
@SuppressLint("StaticFieldLeak")
object CurrencyDatabase {
    const val TYPE_LATEST = "latest"
    const val TYPE_CURRENCY = "currency"

    var context: Context? = null
    private var appDB: AppDatabase? = null

    fun setContext(context: Context): CurrencyDatabase {
        this.context = context
        return this
    }

    fun init(): CurrencyDatabase {
        appDB = AppDatabase.db(context!!)
        return this
    }

    private fun db() = appDB

    fun currencyDao() = db()?.currencyDao()

    @DelicateCoroutinesApi
    fun configCurrency(): CurrencyDatabase {
        GlobalScope.launch {
            val entity = currencyDao()?.fetchData(TYPE_CURRENCY)
            if (entity == null) {
                currencyDao()?.insertCurrency(
                    CurrencyEntity(
                        json = "", type = TYPE_CURRENCY
                    )
                )
            }
        }
        return this
    }

    @DelicateCoroutinesApi
    fun configLatest(): CurrencyDatabase {

        GlobalScope.launch {
            val entity = currencyDao()?.fetchData(TYPE_LATEST)
            if (entity == null) {
                currencyDao()?.insertCurrency(
                    CurrencyEntity(
                        json = "", type = TYPE_LATEST
                    )
                )
            }
        }
        return this
    }
}