package com.intsab.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intsab.core.room.entity.COL_JSON
import com.intsab.core.room.entity.COL_TYPE
import com.intsab.core.room.entity.CurrencyEntity
import com.intsab.core.room.entity.TABLE_CURRENCY


/**
 * Created by intsabhaider
 * on 26,April,2023
 */
@Dao
interface CurrencyDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertCurrency(entity: CurrencyEntity)

    @Query("UPDATE $TABLE_CURRENCY SET $COL_JSON= :json WHERE $COL_TYPE = :type")
    fun updateData(type: String, json: String)

    @Query("SELECT $COL_JSON FROM $TABLE_CURRENCY WHERE $COL_TYPE = :type")
     fun fetchData(type: String): String?
}