package com.intsab.core.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Created by intsabhaider
 * on 01,May,2023
 */
object GsonExt {
    fun toJson(clz: Any): String {
        val gson = GsonBuilder().create()
        return gson.toJson(clz)
    }

    fun Any.anyToJsonString(): String {
        return Gson().toJsonTree(this).asJsonObject.toString()
    }
    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return Gson().fromJson(json, clazz) as T
    }
}