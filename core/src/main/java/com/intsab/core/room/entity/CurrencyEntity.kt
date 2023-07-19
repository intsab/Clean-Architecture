package com.intsab.core.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
const val TABLE_CURRENCY = "currency"
const val COL_JSON = "json_field"
const val COL_TYPE = "type_field"

@Entity(tableName = TABLE_CURRENCY)
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = COL_JSON) val json: String?,
    @ColumnInfo(name = COL_TYPE) val type: String?
)