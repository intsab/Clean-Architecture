package com.intsab.core.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.intsab.core.room.entity.CurrencyEntity

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
@Database(entities = [CurrencyEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase() {

    abstract fun currencyDao(): CurrencyDoa

    companion object {
        private val DB_NAME = "appdb"
        private var dbInstance: AppDatabase? = null

        fun db(context: Context): AppDatabase? {
            if (dbInstance == null) {
                synchronized(AppDatabase::class) {
                    if (dbInstance == null) {
                        dbInstance = Room.databaseBuilder(
                            context, AppDatabase::class.java, DB_NAME
                        ).allowMainThreadQueries().build()
                    }
                }
            }
            return dbInstance
        }
    }
}