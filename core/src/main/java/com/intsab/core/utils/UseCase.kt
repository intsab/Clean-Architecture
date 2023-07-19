package com.intsab.core.utils

/**
 * Created by intsabhaider
 * on 27,April,2023
 */
abstract class UseCase<Type, in Params> where Type : Any {

    companion object {
        private const val TAG = "UseCase"
    }

    abstract suspend fun run(params: Params?): Type
}